package kaptainwutax.seedcracker.cracker.structure.type;

import kaptainwutax.seedcracker.cracker.structure.StructureData;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.seed.ChunkSeeds;
import net.minecraft.util.math.ChunkPos;

public class RarityType extends FeatureType<StructureData> {

	private final float rarity;
	private final double bits;

	public RarityType(int salt, float rarity) {
		super(salt, 1);
		this.rarity = rarity;
		this.bits = Math.log(1.0D / this.rarity) / Math.log(2);
	}

	@Override
	public boolean test(JRand rand, StructureData data, long structureSeed) {
		return rand.nextFloat() < this.rarity;
	}

	@Override
	public ChunkPos getInRegion(JRand rand, long structureSeed, int regionX, int regionZ) {
		long regionSeed = ChunkSeeds.getRegionSeed(structureSeed, regionX, regionZ, this.salt, MCVersion.v1_15);
		rand.setSeed(regionSeed);
		if(rand.nextFloat() >= this.rarity)return null;
		return new ChunkPos(regionX, regionZ);
	}

	@Override
	public double getBits() {
		return this.bits;
	}

}
