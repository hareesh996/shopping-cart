package com.mindtree.dao.product;

import com.google.common.base.Strings;
import com.mindtree.entities.product.Apparel;
import com.mindtree.entities.product.Apparel_;
import com.mindtree.entities.product.Book;
import com.mindtree.entities.product.Book_;
import com.mindtree.web.dto.product.ApparelSearchDto;
import com.mindtree.web.dto.product.BookSearchDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository("productSearchByHibernate")
public class ProductSearchHibernateRepo implements ProductSearchRepository {

    private final EntityManager entityManager;

    ProductSearchHibernateRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> searchBooks(BookSearchDto bookSearchDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> bookCriteriaQuery = criteriaBuilder.createQuery(Book.class); // This is the type that finally result will be evaluated.
        Root<Book> root = bookCriteriaQuery.from(Book.class); // This is like the from clause of the Native Query.
        bookCriteriaQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if (!Strings.isNullOrEmpty(bookSearchDto.getAuthor())) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get(Book_.author)), bookSearchDto.getAuthor().trim().toLowerCase()));
        }
        if (!Strings.isNullOrEmpty(bookSearchDto.getGenre())) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get(Book_.genre)), bookSearchDto.getGenre().trim().toLowerCase()));
        }
        if (!predicates.isEmpty()) {
            bookCriteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        }
        return entityManager.createQuery(bookCriteriaQuery).getResultList();
    }

    @Override
    public List<Apparel> searchApparels(ApparelSearchDto apparelSearchDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Apparel> bookCriteriaQuery = criteriaBuilder.createQuery(Apparel.class); // This is the type that finally result will be evaluated.
        Root<Apparel> root = bookCriteriaQuery.from(Apparel.class); // This is like the from clause of the Native Query.
        bookCriteriaQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if (!Strings.isNullOrEmpty(apparelSearchDto.getBrand())) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get(Apparel_.brand)), apparelSearchDto.getBrand().trim().toLowerCase()));
        }
        if (!Strings.isNullOrEmpty(apparelSearchDto.getType())) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get(Apparel_.type)), apparelSearchDto.getType().trim().toLowerCase()));
        }
        if (!predicates.isEmpty()) {
            bookCriteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        }
        return entityManager.createQuery(bookCriteriaQuery).getResultList();
    }
}
