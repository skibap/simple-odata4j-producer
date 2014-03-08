package de.example.odata4j.simple;

import java.util.Properties;

import org.odata4j.producer.ODataProducer;
import org.odata4j.producer.ODataProducerFactory;

public class SimpleProducerFactory implements ODataProducerFactory {

	@Override
	public ODataProducer create(Properties properties) {
		return new SimpleInMemoryProducer("simple");
	}
}