package kaptainwutax.seedcracker.util.loot;

import com.mojang.serialization.Lifecycle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.loot.context.LootContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resource.DataPackSettings;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.RegistryTracker;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameMode;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldSaveHandler;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.Spawner;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;
import net.minecraft.world.level.storage.LevelStorage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class LootBuilder extends LootContext.Builder {

	private static FakeWorld FAKE_WORLD;

	static {
		try {
			FAKE_WORLD = new FakeWorld();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public LootBuilder() {
		super(FAKE_WORLD);
	}

	//Best code I've ever written.
	//Even better now.
	private static class FakeWorld extends ServerWorld {
		public FakeWorld() throws IOException {
			super(new IntegratedServer(Thread.currentThread(), MinecraftClient.getInstance(), new RegistryTracker.Modifiable(),
							new LevelStorage(MinecraftClient.getInstance().runDirectory.toPath().resolve("foo"),
									MinecraftClient.getInstance().runDirectory.toPath().resolve("bar"), null).createSession("baz"),
							null, null, null, null, null, null, null), Runnable::run,
					new LevelStorage(MinecraftClient.getInstance().runDirectory.toPath().resolve("foo"),
							MinecraftClient.getInstance().runDirectory.toPath().resolve("bar"), null).createSession("baz"),
					new LevelProperties(new LevelInfo("foobar world", GameMode.SURVIVAL, true, Difficulty.NORMAL, false, new GameRules(), DataPackSettings.SAFE_MODE), GeneratorOptions.getDefaultOptions(), Lifecycle.stable()),
					ServerWorld.OVERWORLD, RegistryKey.of(Registry.DIMENSION_TYPE_KEY, new Identifier("overworld")), DimensionType.getOverworldDimensionType(), new WorldGenerationProgressListener() {
				@Override
				public void start(ChunkPos var1) {

				}

				@Override
				public void setChunkStatus(ChunkPos var1, ChunkStatus var2) {

				}

				@Override
				public void stop() {

				}
			}, GeneratorOptions.createOverworldGenerator((new Random()).nextLong()), false, 0L, new ArrayList<Spawner>(), true);
		}
	}

}
