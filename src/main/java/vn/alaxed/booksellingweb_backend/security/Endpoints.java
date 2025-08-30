package vn.alaxed.booksellingweb_backend.security;

public class Endpoints {


    public static final String front_end_host = "http://localhost:3000";
    public static final String[] AUTHENTICATED_GET_ENDPOINTS = {
      "/api/v1/account/me",
      "/cart/**",
      "/wishlist/**",
      "/wish-list/**"
    };
    public static final String[] AUTHENTICATED_PUT_ENDPOINTS = {
      "/api/v1/account/update-profile",
      "/api/v1/account/update-profile/**",
      "/cart/**",
      "/wishlist/**"
    };
    public static final String[] AUTHENTICATED_POST_ENDPOINTS = {
      "/cart/**",
      "/wishlist/**"
    };
     public static final String[] AUTHENTICATED_DEL_ENDPOINTS = {
      "/cart/**",
      "/wishlist/**"
    };

    public static final String[] PUBLIC_GET_ENDPOINTS = {
        "/books",
        "/books/**",
        "/images",
        "/images/**",
        "/users/search/existsByEmail",
        "/users/search/existsByUsername",
        "/api/v1/account/active",
        "/api/v1/account/get",
        "/categories",
        "/categories/**"

    };

    public static final String[] PUBLIC_POST_ENDPOINTS = {
        "/api/v1/account/register",
        "/api/v1/account/login",
      




    };

    public static final String[] ADMIN_GET_ENDPOINTS = {
      "/users",
      "/users/**",
      "/api/v1/book/get-all",
      "/orders",
      "/order-detail",
      "/order-detail/**"

      
      


    };
    public static final String[] ADMIN_PUT_ENDPOINTS = {
      "/books",
      "/books/**",
      "/categories",
      "/categories/**",
      "/users",
      "/users/**",
      "/api/v1/book",
      "/api/v1/book/**"
      
        


    };
    public static final String[] ADMIN_POST_ENDPOINTS = {
      "/books",
      "/images",
      "/image",
      "/image/**",
      "/categories",
      "/categories/**",
      "/api/v1/book",
      "/api/v1/book/**",


        


    };
    public static final String[] ADMIN_DELETE_ENDPOINTS = {
      "/books",
      "/books/**",
      "/images",
      "/images/**",
      "/image",
      "/image/**",
      "/categories",
      "/categories/**"

    
        


    };
}
