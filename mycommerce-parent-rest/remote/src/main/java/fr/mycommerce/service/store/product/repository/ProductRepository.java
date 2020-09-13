package fr.mycommerce.service.store.product.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import fr.mycommerce.service.store.product.entity.Product;

@Repository
public interface ProductRepository extends EntityRepository<Product, Long> {

}