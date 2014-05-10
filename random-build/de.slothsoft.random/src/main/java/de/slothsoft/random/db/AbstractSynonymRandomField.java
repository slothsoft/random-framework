package de.slothsoft.random.db;

import de.slothsoft.random.io.AbstractDatabaseReader;

public abstract class AbstractSynonymRandomField<T> extends
		AbstractRandomField<T> {

	private String[] synonyms = {};

	public AbstractSynonymRandomField(String displayName) {
		super(displayName);
		readSynonyms(displayName);
	}

	protected void readSynonyms(String id) {
		this.synonyms = AbstractDatabaseReader.getInstance().readSynonyms(id);
	}

	@Override
	public boolean isMapped(String attributeName, Class<?> parameterType) {
		if (!isClassMapped(parameterType)) {
			return false;
		}

		for (String synonym : getSynonyms()) {
			if (attributeName.equals(synonym)) {
				return true;
			}
		}
		return false;
	}

	public String[] getSynonyms() {
		return this.synonyms;
	}

}
