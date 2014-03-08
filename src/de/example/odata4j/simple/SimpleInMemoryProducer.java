package de.example.odata4j.simple;

import java.util.HashMap;

import org.core4j.Func;
import org.odata4j.core.OEntities;
import org.odata4j.core.OEntity;
import org.odata4j.core.OEntityKey;
import org.odata4j.producer.EntityResponse;
import org.odata4j.producer.Responses;
import org.odata4j.producer.inmemory.InMemoryProducer;

public class SimpleInMemoryProducer extends InMemoryProducer {
	final HashMap<String, Product> products = new HashMap<String, Product>();

	public SimpleInMemoryProducer(String namespace) {
		super(namespace);

		populate();

		this.register(Product.class, "Products", new Func<Iterable<Product>>() {
			public Iterable<Product> apply() {
				return products.values();
			}
		}, "Key");
	}

	@Override
	public EntityResponse createEntity(String entitySetName, OEntity entity) {
		Product product;
		if (entitySetName.equals("Products")) {
			product = createProduct(entity);
			OEntity newEntity = createNewEntity(entity, product);
			return Responses.entity(newEntity);
		} else {
			return Responses.entity(entity);
		}
	}

	@Override
	public void updateEntity(String entitySetName, OEntity entity) {
		if (entitySetName.equals("Products")) {
			updateProduct(entity);
		}
	}

	@Override
	public void mergeEntity(String entitySetName, OEntity entity) {
		if (entitySetName.equals("Products")) {
			updateProduct(entity);
		}
	}

	@Override
	public void deleteEntity(String entitySetName, OEntityKey entityKey) {
		if (entitySetName.equals("Products")) {
			products.remove(entityKey.asSingleValue().toString());
		}
	}

	private void updateProduct(OEntity entity) {
		Product product = products.get(entity.getEntityKey().asSingleValue()
				.toString());
		product.setDescription(entity.getProperty("Description").getValue()
				.toString());
	}

	private OEntity createNewEntity(OEntity entity, Product product) {
		OEntityKey entityKey = OEntityKey.create(product.getKey());
		OEntity nEntity = OEntities.create(entity.getEntitySet(), entityKey,
				entity.getProperties(), entity.getLinks());
		return nEntity;
	}

	private Product createProduct(OEntity entity) {
		Product product;
		product = new Product(entity.getProperty("Key").getValue().toString(),
				entity.getProperty("Description").getValue().toString());
		products.put(product.getKey(), product);
		return product;
	}

	private void populate() {
		Product product = new Product("IA40001", "USB2 Adapter");
		products.put(product.getKey(), product);
		product = new Product("IA40002", "Cardreader USB2.0");
		products.put(product.getKey(), product);
	}
}
