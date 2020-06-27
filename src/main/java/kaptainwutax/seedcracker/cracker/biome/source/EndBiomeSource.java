package kaptainwutax.seedcracker.cracker.biome.source;

import com.google.common.hash.Hashing;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.TheEndBiomeSource;
import net.minecraft.world.biome.source.VoronoiBiomeAccessType;

public class EndBiomeSource extends TheEndBiomeSource implements IFakeBiomeSource {

	private final long worldSeed;
	private final long hashedWorldSeed;

	public EndBiomeSource(long worldSeed) {
		this(worldSeed,Hashing.sha256().hashLong(worldSeed).asLong());
	}

	public EndBiomeSource(long worldSeed, long hashedWorldSeed) {
		super(worldSeed);
		this.worldSeed = worldSeed;
		this.hashedWorldSeed = hashedWorldSeed;
	}

	@Override
	public long getWorldSeed() {
		return this.worldSeed;
	}

	@Override
	public long getHashedWorldSeed() {
		return this.hashedWorldSeed;
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
