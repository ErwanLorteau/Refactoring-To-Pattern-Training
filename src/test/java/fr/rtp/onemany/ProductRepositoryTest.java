package fr.rtp.onemany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest  {

  private Product fireTruck = new Product("f1234", "Fire Truck", Color.red, 8.95f, ProductSize.MEDIUM);
  private Product barbieClassic = new Product("b7654", "Barbie Classic", Color.yellow, 15.95f, ProductSize.SMALL);
  private Product frisbee = new Product("f4321", "Frisbee", Color.pink, 9.99f, ProductSize.LARGE);
  private Product baseball = new Product("b2343", "Baseball", Color.white, 8.95f, ProductSize.NOT_APPLICABLE);
  private Product toyConvertible = new Product("p1112", "Toy Porsche Convertible", Color.red, 230.00f,
      ProductSize.NOT_APPLICABLE);

  private ProductRepository repository;

  @BeforeEach
  protected void setUp() {
    repository = new ProductRepository();
    repository.add(fireTruck);
    repository.add(barbieClassic);
    repository.add(frisbee);
    repository.add(baseball);
    repository.add(toyConvertible);
  }

  @Test
  public void testFindByColor() {
    List<Product> foundProducts = repository.selectBy(new ColorSpec(Color.red));
    assertEquals(2, foundProducts.size(), "found 2 red products");
    assertTrue(foundProducts.contains(fireTruck), "found fireTruck");
    assertTrue(foundProducts.contains(toyConvertible), "found Toy Porsche Convertible");
  }

  @Test
  public void testFindByColorSizeAndBelowPrice() {
    List<Spec> specs = new ArrayList<Spec>();
    specs.add(new ColorSpec(Color.red));
    specs.add(new SizeSpec(ProductSize.SMALL));
    specs.add(new BelowPriceSpec(10.00));

    List<Product> foundProducts = repository.selectBy(specs);
    assertEquals(0, foundProducts.size(), "small red products below $10.00");
  }
}
