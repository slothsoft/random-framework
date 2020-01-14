package de.slothsoft.random.types.wordgen;

import java.io.IOException;
import java.io.InputStream;

/**
 * Standard {@link WordGeneratorConfig}s.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public enum StandardWordGeneratorConfig implements WordGeneratorConfig {
	/** An even distribution of letters. */
	EVEN_DISTRIBUTION {

		@Override
		WordGeneratorConfig createConfig() {
			return new DefaultWordGeneratorConfig();
		}
	},

	/** A English distribution of letters. */
	ENGLISH {

		@Override
		WordGeneratorConfig createConfig() {
			try (InputStream input = getClass().getResourceAsStream("english.properties")) {
				return WordGeneratorConfigUtil.loadConfig(input);
			} catch (final IOException e) {
				throw new RuntimeException("Something went very wrong reading the English config!", e);
			}
		}
	},

	/** A German distribution of letters. */
	GERMAN {

		@Override
		WordGeneratorConfig createConfig() {
			try (InputStream input = getClass().getResourceAsStream("german.properties")) {
				return WordGeneratorConfigUtil.loadConfig(input);
			} catch (final IOException e) {
				throw new RuntimeException("Something went very wrong reading the German config!", e);
			}
		}
	};

	private WordGeneratorConfig delegate;

	private WordGeneratorConfig getDelegate() {
		if (this.delegate == null) {
			this.delegate = createConfig();
		}
		return this.delegate;
	}

	abstract WordGeneratorConfig createConfig();

	@Override
	public char[] getSupportedCharacters() {
		return getDelegate().getSupportedCharacters();
	}

	@Override
	public double getStandardWordLength() {
		return getDelegate().getStandardWordLength();
	}

	@Override
	public double getProbability(char c) {
		return getDelegate().getProbability(c);
	}

}
