package de.slothsoft.random.db;

import java.util.Set;

import de.slothsoft.random.Option;
import de.slothsoft.random.io.AbstractDatabaseReader;

public class AttributeRandomField extends AbstractSynonymRandomField<String> {

	private String[] attributes;
	private String attributeId;

	public AttributeRandomField(String file, String displayName) {
		super(displayName);
		this.attributeId = file;
	}

	public String[] getAttributes() {
		if (this.attributes == null) {
			readAttributes(this.attributeId);
			if (this.attributes == null) {
				// so not every call to the getter calls the file if something
				// went wrong
				this.attributes = new String[0];
			}
		}
		return this.attributes;
	}

	@Override
	public String getRandomAttribute(String attribute, Class<?> attributeClass,
			Set<Option> options) {
		if (getAttributes().length == 0) {
			return "";
		}
		return this.attributes[RANDOM.nextInt(this.attributes.length)];
	}

	protected void readAttributes(String id) {
		this.attributes = AbstractDatabaseReader.getInstance().readAttributes(
				id);
	}

	@Override
	public Class<?>[] getAttributeClasses() {
		return new Class[] { String.class };
	}
}
