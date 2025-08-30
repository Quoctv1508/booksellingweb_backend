package vn.alaxed.booksellingweb_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.alaxed.booksellingweb_backend.dao.BookRepository;
import vn.alaxed.booksellingweb_backend.dao.OrderDetailRepository;
import vn.alaxed.booksellingweb_backend.dao.OrderRepository;
import vn.alaxed.booksellingweb_backend.dao.UserRepository;
import vn.alaxed.booksellingweb_backend.dto.CartUpdateRequest;
import vn.alaxed.booksellingweb_backend.dto.OrderDTO;
import vn.alaxed.booksellingweb_backend.dto.OrderDetailDTO;
import vn.alaxed.booksellingweb_backend.entity.Book;
import vn.alaxed.booksellingweb_backend.entity.Order;
import vn.alaxed.booksellingweb_backend.entity.OrderDetail;
import vn.alaxed.booksellingweb_backend.entity.User;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/get")
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserDetails user) {
        User u = userRepository.findByUsername(user.getUsername());
        Order order = orderRepository.findByUserAndPaymentStatus(u, "PENDING").orElse(null);
        if (order == null) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<OrderDetail> details = orderDetailRepository.findAllByOrder(order);
        
        List<OrderDetailDTO> odtos = details.stream()
                .map(d -> new OrderDetailDTO(d)).toList();
        return ResponseEntity.ok(odtos);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(
            @AuthenticationPrincipal UserDetails user,
            @RequestParam int bookId,
            @RequestParam int quantity) {
        User u = userRepository.findByUsername(user.getUsername());
        // Tìm order có trạng thái PENDING
        Order order = orderRepository.findByUserAndPaymentStatus(u, "PENDING").orElseGet(() -> {
            // Không có thì thêm mới
            Order o = new Order();
            o.setUser(u);
            o.setPaymentStatus("PENDING");
            o.setCreatedAt(new Date(System.currentTimeMillis()));
            return orderRepository.save(o);
        });
        Book bookExisting = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Optional<OrderDetail> existing = orderDetailRepository.findByOrderAndBook(order, bookExisting);
        if (quantity <= 0) {
            existing.ifPresent(orderDetailRepository::delete); // xoá nếu quantity = 0
        } else if (existing.isPresent()) {
            OrderDetail detail = existing.get();
            detail.setQuantity(quantity);
            orderDetailRepository.save(detail);
        } else {
            Book book = bookRepository.findById(bookId).orElseThrow();
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setBook(book);
            detail.setQuantity(quantity);
            detail.setSalePrice(book.getSalePrice());
            orderDetailRepository.save(detail);
        }

        OrderDTO orderDTO = new OrderDTO(order);

        return ResponseEntity.ok(orderDTO);

    }

    @PostMapping("/add-one")
    public ResponseEntity<?> addOneToCart(
            @AuthenticationPrincipal UserDetails user,
            @RequestParam int bookId
           ) {
        User u = userRepository.findByUsername(user.getUsername());
        // Tìm order có trạng thái PENDING
        Order order = orderRepository.findByUserAndPaymentStatus(u, "PENDING").orElseGet(() -> {
            // Không có thì thêm mới
            Order o = new Order();
            o.setUser(u);
            o.setPaymentStatus("PENDING");
            o.setCreatedAt(new Date(System.currentTimeMillis()));
            return orderRepository.save(o);
        });
        Book bookExisting = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Optional<OrderDetail> existing = orderDetailRepository.findByOrderAndBook(order, bookExisting);
        if(existing.isPresent()){
            OrderDetail dt = existing.get();
            dt.setQuantity(existing.get().getQuantity() + 1);
            orderDetailRepository.save(dt);
        }else{
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setBook(bookExisting);
            detail.setQuantity(1);
            detail.setSalePrice(bookExisting.getSalePrice());
            orderDetailRepository.save(detail);
        }
       

        OrderDTO orderDTO = new OrderDTO(order);

        return ResponseEntity.ok(orderDTO);

    }

    @PostMapping("/add-many")
    public ResponseEntity<?> addManyToCart(
            @AuthenticationPrincipal UserDetails user,
            @RequestParam int bookId,
            @RequestParam int quantity
           ) {
        User u = userRepository.findByUsername(user.getUsername());
        // Tìm order có trạng thái PENDING
        Order order = orderRepository.findByUserAndPaymentStatus(u, "PENDING").orElseGet(() -> {
            // Không có thì thêm mới
            Order o = new Order();
            o.setUser(u);
            o.setPaymentStatus("PENDING");
            o.setCreatedAt(new Date(System.currentTimeMillis()));
            return orderRepository.save(o);
        });
        Book bookExisting = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Optional<OrderDetail> existing = orderDetailRepository.findByOrderAndBook(order, bookExisting);
        if(existing.isPresent()){
            OrderDetail dt = existing.get();
            dt.setQuantity(existing.get().getQuantity() + quantity);
            orderDetailRepository.save(dt);
        }else{
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setBook(bookExisting);
            detail.setQuantity(1);
            detail.setSalePrice(bookExisting.getSalePrice());
            orderDetailRepository.save(detail);
        }
       

        OrderDTO orderDTO = new OrderDTO(order);

        return ResponseEntity.ok(orderDTO);

    }

    @PostMapping("/batch-update")
    public ResponseEntity<?> updateCartBatch(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody List<CartUpdateRequest> updates) {
        
        
        //Lấy user từ token
        User u = userRepository.findByUsername(userDetails.getUsername());
        //Lấy order với trạng thái Pending
        Order order = orderRepository.findByUserAndPaymentStatus(u, "PENDING").orElseThrow();
        //Duyệt từ phần tử orderdetail để update
        for (CartUpdateRequest dto : updates) {
            //Tìm sách có trùng id với sách truyền vào
            Book book = bookRepository.findById(dto.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            //Kiểm tra OrderDetail đã tồn tại hay chưa với điều kiện theo Order và Book
            Optional<OrderDetail> existing = orderDetailRepository.findByOrderAndBook(order, book);
            if (dto.getQuantity() <= 0) {    //Nếu số lượng cập nhật bé hơn hoặc bằng không
                existing.ifPresent(orderDetailRepository::delete); // thực hiện xóa luôn Order detail
            } else if (existing.isPresent()) {
                OrderDetail orderDetail = existing.get();
                orderDetail.setQuantity(dto.getQuantity());
                orderDetailRepository.save(orderDetail);
            } else {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setBook(book);
                orderDetail.setQuantity(dto.getQuantity());
                orderDetail.setSalePrice(book.getSalePrice());
                orderDetailRepository.save(orderDetail);

            }

           
        }

         Map<String, Object> body = Map.of(
                "status", "ok",
                "updated", updates.size(),
                "message", "Batch updated successfully"
            );
        return ResponseEntity.ok(body);
    }

   


}
