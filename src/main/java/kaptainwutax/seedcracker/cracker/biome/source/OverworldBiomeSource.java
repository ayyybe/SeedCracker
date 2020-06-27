package kaptainwutax.seedcracker.cracker.biome.source;

import com.google.common.hash.Hashing;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import net.minecraft.world.biome.source.VoronoiBiomeAccessType;
import net.minecraft.client.world.GeneratorType;

public class OverworldBiomeSource extends VanillaLayeredBiomeSource implements IFakeBiomeSource {

	private final long worldSeed;
	private final long hashedWorldSeed;
	private final GeneratorType generatorType;

	public OverworldBiomeSource(long worldSeed, GeneratorType generatorType) {
		super(worldSeed, false, false);
		this.worldSeed = worldSeed;
		this.hashedWorldSeed = Hashing.sha256().hashLong(worldSeed).asLong();
		this.generatorType = generatorType;
	}

	@Override
	public long getWorldSeed() {
		return this.worldSeed;
	}

	@Override
	public long getHashedWorldSeed() {
		return this.hashedWorldSeed;
	}

	public GeneratorType getGeneratorType() {
		return this.generatorType;
	}

	@Override
	public Biome sample(int x, int y, int z) {
		return VoronoiBiomeAccessType.INSTANCE.getBiome(this.getHashedWorldSeed(), x, y, z, this);
	}

	@Override
	public BiomeSource getBiomeSource() {
		return this;
	}

}
