package com.mindtree.web.controller.product;

import com.mindtree.services.product.ProductService;
import com.mindtree.web.dto.Response;
import com.mindtree.web.dto.product.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("search")
public class ProductController {
    private ProductService productService;

    private ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("books")
    public ResponseEntity<Response<List<BookDto>>> searchProducts(@RequestBody() BookSearchDto bookSearchDto) {
        return Response.<List<BookDto>>builder().ok(this.productService.searchBooks(bookSearchDto));
    }

    @PostMapping("apparels")
    public ResponseEntity<Response<List<ApparelDto>>> searchProducts(@RequestBody() ApparelSearchDto apparelSearchDto) {
        return Response.<List<ApparelDto>>builder().ok(this.productService.searchApparels(apparelSearchDto));
    }

}
