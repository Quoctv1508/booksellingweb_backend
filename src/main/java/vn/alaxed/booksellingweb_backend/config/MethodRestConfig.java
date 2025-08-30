package vn.alaxed.booksellingweb_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import vn.alaxed.booksellingweb_backend.entity.Category;
import vn.alaxed.booksellingweb_backend.entity.User;

@Configuration
public class MethodRestConfig implements RepositoryRestConfigurer {
    
    private String url = "http://localhost:3000";
    
    @Autowired
    private EntityManager entityManager;


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
       HttpMethod[] blockMethods = {
            HttpMethod.POST,
            HttpMethod.PUT,
            HttpMethod.PATCH,
            HttpMethod.DELETE,
       };

       // expose ids
       config.exposeIdsFor(entityManager.getMetamodel()
                             .getEntities()
                             .stream()
                             .map(Type::getJavaType)
                             .toArray(Class[]::new));   

        //CORS configuration
        cors.addMapping("/**")
            .allowedOrigins(url)
            .allowedMethods("GET", "PUT", "POST", "DELETE");
                
        // blockHttpMethods(Category.class, config, blockMethods);
        // HttpMethod[] deleteMethod = {
        //     HttpMethod.DELETE
        // };
        // blockHttpMethods(User.class, config, deleteMethod);

    }

    private void blockHttpMethods(Class c, 
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] methods
                                ){
        config.getExposureConfiguration()
                .forDomainType(c)
                .withItemExposure((metdata, httpMethods) ->httpMethods.disable(methods))
                .withCollectionExposure((metdata, httpMethods) ->httpMethods.disable(methods));
    }
    
    
}
