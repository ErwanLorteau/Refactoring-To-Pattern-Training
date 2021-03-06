package fr.rtp.onemany.predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import fr.rtp.onemany.Product;
import fr.rtp.onemany.Spec;

import javax.annotation.Nonnull;

public class PredicateProductRepository {

	private List<Product> products = new ArrayList<Product>();

	public Iterator<Product> iterator() {
		return products.iterator();
	}
	public List<Product> selectBy(Predicate<Product> spec) {
		Collection<Product> foundProducts = Collections2.filter(products, spec);

		return new ArrayList<Product>(foundProducts);
	}

	@Nonnull
	public List<Product> selectBy(@Nonnull Spec spec) {
		
		return selectBy((Predicate<Product>)spec);
	}

	public void add(Product product) {
		products.add(product);

	}
}
