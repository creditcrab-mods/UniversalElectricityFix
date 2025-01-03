package universalelectricity.prefab.ore;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.ChunkProviderHell;

public class OreGenReplace extends OreGenBase {

   public int minGenerateLevel;
   public int maxGenerateLevel;
   public int amountPerChunk;
   public int amountPerBranch;
   public Block replaceID;
   public boolean ignoreSurface = false;
   public boolean ignoreNether = true;
   public boolean ignoreEnd = true;


   public OreGenReplace(String name, String oreDiectionaryName, ItemStack stack, Block replaceID, int minGenerateLevel, int maxGenerateLevel, int amountPerChunk, int amountPerBranch, String harvestTool, int harvestLevel) {
      super(name, oreDiectionaryName, stack, harvestTool, harvestLevel);
      this.minGenerateLevel = minGenerateLevel;
      this.maxGenerateLevel = maxGenerateLevel;
      this.amountPerChunk = amountPerChunk;
      this.amountPerBranch = amountPerBranch;
      this.replaceID = replaceID;
   }

   public void generate(World world, Random random, int varX, int varZ) {
      try {
         for(int e = 0; e < this.amountPerChunk; ++e) {
            int x = varX + random.nextInt(16);
            int z = varZ + random.nextInt(16);
            int y = random.nextInt(Math.max(this.maxGenerateLevel - this.minGenerateLevel, 0)) + this.minGenerateLevel;
            this.generateReplace(world, random, x, y, z);
         }
      } catch (Exception var9) {
         System.out.println("Error generating ore: " + super.name);
         var9.printStackTrace();
      }

   }

   public boolean generateReplace(World par1World, Random par2Random, int par3, int par4, int par5) {
      float var6 = par2Random.nextFloat() * 3.1415927F;
      double var7 = (double)((float)(par3 + 8) + MathHelper.sin(var6) * (float)this.amountPerBranch / 8.0F);
      double var9 = (double)((float)(par3 + 8) - MathHelper.sin(var6) * (float)this.amountPerBranch / 8.0F);
      double var11 = (double)((float)(par5 + 8) + MathHelper.cos(var6) * (float)this.amountPerBranch / 8.0F);
      double var13 = (double)((float)(par5 + 8) - MathHelper.cos(var6) * (float)this.amountPerBranch / 8.0F);
      double var15 = (double)(par4 + par2Random.nextInt(3) - 2);
      double var17 = (double)(par4 + par2Random.nextInt(3) - 2);

      for(int var19 = 0; var19 <= this.amountPerBranch; ++var19) {
         double var20 = var7 + (var9 - var7) * (double)var19 / (double)this.amountPerBranch;
         double var22 = var15 + (var17 - var15) * (double)var19 / (double)this.amountPerBranch;
         double var24 = var11 + (var13 - var11) * (double)var19 / (double)this.amountPerBranch;
         double var26 = par2Random.nextDouble() * (double)this.amountPerBranch / 16.0D;
         double var28 = (double)(MathHelper.sin((float)var19 * 3.1415927F / (float)this.amountPerBranch) + 1.0F) * var26 + 1.0D;
         double var30 = (double)(MathHelper.sin((float)var19 * 3.1415927F / (float)this.amountPerBranch) + 1.0F) * var26 + 1.0D;
         int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
         int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
         int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
         int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
         int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
         int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

         for(int var38 = var32; var38 <= var35; ++var38) {
            double var39 = ((double)var38 + 0.5D - var20) / (var28 / 2.0D);
            if(var39 * var39 < 1.0D) {
               for(int var41 = var33; var41 <= var36; ++var41) {
                  double var42 = ((double)var41 + 0.5D - var22) / (var30 / 2.0D);
                  if(var39 * var39 + var42 * var42 < 1.0D) {
                     for(int var44 = var34; var44 <= var37; ++var44) {
                        double var45 = ((double)var44 + 0.5D - var24) / (var28 / 2.0D);
                        Block block = par1World.getBlock(var38, var41, var44);
                        if(var39 * var39 + var42 * var42 + var45 * var45 < 1.0D && (this.replaceID == Blocks.air || block == this.replaceID)) {
                           par1World.setBlock(var38, var41, var44, super.oreID, super.oreMeta, 2);
                        }
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   public boolean isOreGeneratedInWorld(World world, IChunkProvider chunkGenerator) {
      return !super.shouldGenerate?false:(this.ignoreSurface && chunkGenerator instanceof ChunkProviderGenerate?false:(this.ignoreNether && chunkGenerator instanceof ChunkProviderHell?false:!this.ignoreEnd || !(chunkGenerator instanceof ChunkProviderEnd)));
   }
}
