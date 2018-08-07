package com.thoughtworks.mallbackend.service;

import com.thoughtworks.mallbackend.entity.Product;
import com.thoughtworks.mallbackend.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    public void should_get_all_product() throws ParseException {
        // given
        List<Product> products = new ArrayList<>();
        products.add(new Product("Drinking Water",
                2.0,
                "bottle",
                "water",
                "drinking water",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-08-05 20:20:00"),
                "China"));
        given(productRepository.findAll())
                .willReturn(products);

        // when
        List<Product> actual = productService.getAll();

        // then
        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.get(0).getName()).isEqualTo("Drinking Water");
    }

    @Test
    public void should_add_a_record_given_a_product() throws ParseException {
        // given
        Product product = new Product("Drinking Water",
                2.0,
                "bottle",
                "water",
                "drinking water",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-08-05 20:20:00"),
                "China");
        product.setId(1L);

        given(productRepository.save(product))
                .willReturn(product);

        // when
        Long actual = productService.add(product);

        //then
        verify(productRepository,times(1)).save(product);
        assertThat(actual).isEqualTo(1L);
    }

    @Test
    public void should_update_a_record_given_a_product() throws ParseException {
        //given
        Product oldProduct = new Product("Drinking Water",
                2.0,
                "bottle",
                "water",
                "drinking water",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-08-05 20:20:00"),
                "China");
        oldProduct.setId(1L);
        Product newProduct = new Product("Drinking Water",
                20.0,
                "bottle",
                "water",
                "drinking water",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-08-05 20:20:00"),
                "China");
        newProduct.setId(1L);
        Optional<Product> productOptional = Optional.of(oldProduct);

        given(productRepository.save(oldProduct))
                .willReturn(newProduct);
        given(productRepository.findById(anyLong()))
                .willReturn(productOptional);

        // when
        productService.update(1L,oldProduct);

        //then
        verify(productRepository,times(1)).save(oldProduct);
        verify(productRepository,times(1)).findById(1L);
    }
}
