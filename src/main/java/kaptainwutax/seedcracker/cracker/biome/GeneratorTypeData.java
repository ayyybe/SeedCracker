package kaptainwutax.seedcracker.cracker.biome;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.world.GeneratorType;

import java.util.Set;

public class GeneratorTypeData {

	private static final Set<GeneratorType> WHITELIST = ImmutableSet.of(
			GeneratorType.DEFAULT, GeneratorType.AMPLIFIED
	);

	private final GeneratorType generatorType;
	private final boolean isSupported;

	public GeneratorTypeData(GeneratorType generatorType) {
		this.generatorType = generatorType;
		this.isSupported = isSupported(generatorType);
	}

	public GeneratorType getGeneratorType() {
		return this.generatorType;
	}

	public boolean isSupported() {
		return this.isSupported;
	}

	public static boolean isSupported(GeneratorType generatorType) {
		return WHITELIST.contains(generatorType);
	}

}
