package com.qzimyion.magmavision;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class MagmaVisionRenderer extends LiquidBlockRenderer {

    private static boolean isNeighborSameFluid(FluidState fluidState, FluidState fluidState2) {
        return fluidState2.getType().isSame(fluidState.getType());
    }

    private int getLightColor(BlockAndTintGetter blockAndTintGetter, BlockPos blockPos) {
        int i = LevelRenderer.getLightColor(blockAndTintGetter, blockPos);
        int j = LevelRenderer.getLightColor(blockAndTintGetter, blockPos.above());
        int k = i & 255;
        int l = j & 255;
        int m = i >> 16 & 255;
        int n = j >> 16 & 255;
        return (Math.max(k, l)) | (Math.max(m, n)) << 16;
    }

    private static boolean isFaceOccludedByNeighbor(BlockGetter blockGetter, BlockPos blockPos, Direction direction, float f, BlockState blockState) {
        return isFaceOccludedByState(blockGetter, direction, f, blockPos.relative(direction), blockState);
    }

    private static boolean isFaceOccludedByState(BlockGetter blockGetter, Direction direction, float f, BlockPos blockPos, BlockState blockState) {
        if (blockState.canOcclude()) {
            VoxelShape voxelShape = Shapes.box(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
            VoxelShape voxelShape2 = blockState.getOcclusionShape(blockGetter, blockPos);
            return Shapes.blockOccudes(voxelShape, voxelShape2, direction);
        } else {
            return false;
        }
    }

    public static boolean shouldRenderFace(BlockAndTintGetter blockAndTintGetter, BlockPos blockPos, FluidState fluidState, BlockState blockState, Direction direction, FluidState fluidState2) {
        return !isFaceOccludedBySelf(blockAndTintGetter, blockPos, blockState, direction) && !isNeighborSameFluid(fluidState, fluidState2);
    }

    private static boolean isFaceOccludedBySelf(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, Direction direction) {
        return isFaceOccludedByState(blockGetter, direction.getOpposite(), 1.0F, blockPos, blockState);
    }

    private float getHeight(BlockAndTintGetter blockAndTintGetter, Fluid fluid, BlockPos blockPos) {
        BlockState blockState = blockAndTintGetter.getBlockState(blockPos);
        return this.getHeight(blockAndTintGetter, fluid, blockPos, blockState, blockState.getFluidState());
    }

    private float getHeight(BlockAndTintGetter blockAndTintGetter, Fluid fluid, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
        if (fluid.isSame(fluidState.getType())) {
            BlockState blockState2 = blockAndTintGetter.getBlockState(blockPos.above());
            return fluid.isSame(blockState2.getFluidState().getType()) ? 1.0F : fluidState.getOwnHeight();
        } else {
            return !blockState.isSolid() ? 0.0F : -1.0F;
        }
    }

    private float calculateAverageHeight(BlockAndTintGetter blockAndTintGetter, Fluid fluid, float f, float g, float h, BlockPos blockPos) {
        if (!(h >= 1.0F) && !(g >= 1.0F)) {
            float[] fs = new float[2];
            if (h > 0.0F || g > 0.0F) {
                float i = this.getHeight(blockAndTintGetter, fluid, blockPos);
                if (i >= 1.0F) {
                    return 1.0F;
                }

                this.addWeightedHeight(fs, i);
            }

            this.addWeightedHeight(fs, f);
            this.addWeightedHeight(fs, h);
            this.addWeightedHeight(fs, g);
            return fs[0] / fs[1];
        } else {
            return 1.0F;
        }
    }

    private void addWeightedHeight(float[] fs, float f) {
        if (f >= 0.8F) {
            fs[0] += f * 10.0F;
            fs[1] += 10.0F;
        } else if (f >= 0.0F) {
            fs[0] += f;
            fs[1] += 1.0F;
        }

    }

    @Override
    public void tesselate(BlockAndTintGetter blockAndTintGetter, BlockPos blockPos, VertexConsumer vertexConsumer, BlockState blockState, FluidState fluidState) {
        try{
            Fluid fluid = fluidState.getType();
            if (fluid == Fluids.LAVA || fluid == Fluids.FLOWING_LAVA) {
                TextureAtlasSprite[] sprites = this.lavaIcons;
                int i = 16777215;
                float f = (float) (i >> 16 & 255) / 255.0F;
                float g = (float) (i >> 8 & 255) / 255.0F;
                float h = (float) (i & 255) / 255.0F;
                BlockState blockState2 = blockAndTintGetter.getBlockState(blockPos.relative(Direction.DOWN));
                FluidState fluidState2 = blockState2.getFluidState();
                BlockState blockState3 = blockAndTintGetter.getBlockState(blockPos.relative(Direction.UP));
                FluidState fluidState3 = blockState3.getFluidState();
                BlockState blockState4 = blockAndTintGetter.getBlockState(blockPos.relative(Direction.NORTH));
                FluidState fluidState4 = blockState4.getFluidState();
                BlockState blockState5 = blockAndTintGetter.getBlockState(blockPos.relative(Direction.SOUTH));
                FluidState fluidState5 = blockState5.getFluidState();
                BlockState blockState6 = blockAndTintGetter.getBlockState(blockPos.relative(Direction.WEST));
                FluidState fluidState6 = blockState6.getFluidState();
                BlockState blockState7 = blockAndTintGetter.getBlockState(blockPos.relative(Direction.EAST));
                FluidState fluidState7 = blockState7.getFluidState();
                boolean bl2 = !isNeighborSameFluid(fluidState, fluidState3);
                boolean bl3 = shouldRenderFace(blockAndTintGetter, blockPos, fluidState, blockState, Direction.DOWN, fluidState2) && !isFaceOccludedByNeighbor(blockAndTintGetter, blockPos, Direction.DOWN, 0.8888889F, blockState2);
                boolean bl4 = shouldRenderFace(blockAndTintGetter, blockPos, fluidState, blockState, Direction.NORTH, fluidState4);
                boolean bl5 = shouldRenderFace(blockAndTintGetter, blockPos, fluidState, blockState, Direction.SOUTH, fluidState5);
                boolean bl6 = shouldRenderFace(blockAndTintGetter, blockPos, fluidState, blockState, Direction.WEST, fluidState6);
                boolean bl7 = shouldRenderFace(blockAndTintGetter, blockPos, fluidState, blockState, Direction.EAST, fluidState7);
                if (bl2 || bl3 || bl7 || bl6 || bl4 || bl5) {
                    float j = blockAndTintGetter.getShade(Direction.DOWN, true);
                    float k = blockAndTintGetter.getShade(Direction.UP, true);
                    float l = blockAndTintGetter.getShade(Direction.NORTH, true);
                    float m = blockAndTintGetter.getShade(Direction.WEST, true);
                    float n = this.getHeight(blockAndTintGetter, fluid, blockPos);
                    float o;
                    float p;
                    float q;
                    float r;
                    if (n >= 1.0F) {
                        o = 1.0F;
                        p = 1.0F;
                        q = 1.0F;
                        r = 1.0F;
                    } else {
                        float s = this.getHeight(blockAndTintGetter, fluid, blockPos.north());
                        float t = this.getHeight(blockAndTintGetter, fluid, blockPos.south());
                        float u = this.getHeight(blockAndTintGetter, fluid, blockPos.east());
                        float v = this.getHeight(blockAndTintGetter, fluid, blockPos.west());
                        o = this.calculateAverageHeight(blockAndTintGetter, fluid, n, s, u, blockPos.relative(Direction.NORTH).relative(Direction.EAST));
                        p = this.calculateAverageHeight(blockAndTintGetter, fluid, n, s, v, blockPos.relative(Direction.NORTH).relative(Direction.WEST));
                        q = this.calculateAverageHeight(blockAndTintGetter, fluid, n, t, u, blockPos.relative(Direction.SOUTH).relative(Direction.EAST));
                        r = this.calculateAverageHeight(blockAndTintGetter, fluid, n, t, v, blockPos.relative(Direction.SOUTH).relative(Direction.WEST));
                    }

                    double d = blockPos.getX() & 15;
                    double e = blockPos.getY() & 15;
                    double w = (blockPos.getZ() & 15);
                    float y = bl3 ? 0.001F : 0.0F;
                    if (bl2 && !isFaceOccludedByNeighbor(blockAndTintGetter, blockPos, Direction.UP, Math.min(Math.min(p, r), Math.min(q, o)), blockState3)) {
                        p -= 0.001F;
                        r -= 0.001F;
                        q -= 0.001F;
                        o -= 0.001F;
                        Vec3 vec3 = fluidState.getFlow(blockAndTintGetter, blockPos);
                        float z;
                        float ab;
                        float ad;
                        float af;
                        float aa;
                        float ac;
                        float ae;
                        float ag;
                        if (vec3.x == (double) 0.0F && vec3.z == (double) 0.0F) {
                            TextureAtlasSprite textureAtlasSprite = sprites[0];
                            z = textureAtlasSprite.getU(0.0F);
                            aa = textureAtlasSprite.getV(0.0F);
                            ab = z;
                            ac = textureAtlasSprite.getV(16.0F);
                            ad = textureAtlasSprite.getU(16.0F);
                            ae = ac;
                            af = ad;
                            ag = aa;
                        } else {
                            TextureAtlasSprite textureAtlasSprite = sprites[1];
                            float ah = (float) Mth.atan2(vec3.z, vec3.x) - ((float) Math.PI / 2F);
                            float ai = Mth.sin(ah) * 0.25F;
                            float aj = Mth.cos(ah) * 0.25F;
                            z = textureAtlasSprite.getU(8.0F + (-aj - ai) * 16.0F);
                            aa = textureAtlasSprite.getV(8.0F + (-aj + ai) * 16.0F);
                            ab = textureAtlasSprite.getU(8.0F + (-aj + ai) * 16.0F);
                            ac = textureAtlasSprite.getV(8.0F + (aj + ai) * 16.0F);
                            ad = textureAtlasSprite.getU(8.0F + (aj + ai) * 16.0F);
                            ae = textureAtlasSprite.getV(8.0F + (aj - ai) * 16.0F);
                            af = textureAtlasSprite.getU(8.0F + (aj - ai) * 16.0F);
                            ag = textureAtlasSprite.getV(8.0F + (-aj - ai) * 16.0F);
                        }

                        float al = (z + ab + ad + af) / 4.0F;
                        float ah = (aa + ac + ae + ag) / 4.0F;
                        float ai = Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(Blocks.LAVA.defaultBlockState()).getParticleIcon().uvShrinkRatio();
                        z = Mth.lerp(ai, z, al);
                        ab = Mth.lerp(ai, ab, al);
                        ad = Mth.lerp(ai, ad, al);
                        af = Mth.lerp(ai, af, al);
                        aa = Mth.lerp(ai, aa, ah);
                        ac = Mth.lerp(ai, ac, ah);
                        ae = Mth.lerp(ai, ae, ah);
                        ag = Mth.lerp(ai, ag, ah);
                        int am = this.getLightColor(blockAndTintGetter, blockPos);
                        float ak = k * f;
                        float an = k * g;
                        float ao = k * h;
                        this.vertexWithCustomAlpha(vertexConsumer, d + (double) 0.0F, e + (double) p, w + (double) 0.0F, ak, an, ao, z, aa, am);
                        this.vertexWithCustomAlpha(vertexConsumer, d + (double) 0.0F, e + (double) r, w + (double) 1.0F, ak, an, ao, ab, ac, am);
                        this.vertexWithCustomAlpha(vertexConsumer, d + (double) 1.0F, e + (double) q, w + (double) 1.0F, ak, an, ao, ad, ae, am);
                        this.vertexWithCustomAlpha(vertexConsumer, d + (double) 1.0F, e + (double) o, w + (double) 0.0F, ak, an, ao, af, ag, am);
                        if (fluidState.shouldRenderBackwardUpFace(blockAndTintGetter, blockPos.above())) {
                            this.vertexWithCustomAlpha(vertexConsumer, d + (double) 0.0F, e + (double) p, w + (double) 0.0F, ak, an, ao, z, aa, am);
                            this.vertexWithCustomAlpha(vertexConsumer, d + (double) 1.0F, e + (double) o, w + (double) 0.0F, ak, an, ao, af, ag, am);
                            this.vertexWithCustomAlpha(vertexConsumer, d + (double) 1.0F, e + (double) q, w + (double) 1.0F, ak, an, ao, ad, ae, am);
                            this.vertexWithCustomAlpha(vertexConsumer, d + (double) 0.0F, e + (double) r, w + (double) 1.0F, ak, an, ao, ab, ac, am);
                        }
                    }

                    if (bl3) {
                        float z = Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(Blocks.LAVA.defaultBlockState()).getParticleIcon().getU0();
                        float ab = Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(Blocks.LAVA.defaultBlockState()).getParticleIcon().getU1();
                        float ad = Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(Blocks.LAVA.defaultBlockState()).getParticleIcon().getV0();
                        float af = Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(Blocks.LAVA.defaultBlockState()).getParticleIcon().getV1();
                        int ap = this.getLightColor(blockAndTintGetter, blockPos.below());
                        float ac = j * f;
                        float ae = j * g;
                        float ag = j * h;
                        this.vertexWithCustomAlpha(vertexConsumer, d, e + (double) y, w + (double) 1.0F, ac, ae, ag, z, af, ap);
                        this.vertexWithCustomAlpha(vertexConsumer, d, e + (double) y, w, ac, ae, ag, z, ad, ap);
                        this.vertexWithCustomAlpha(vertexConsumer, d + (double) 1.0F, e + (double) y, w, ac, ae, ag, ab, ad, ap);
                        this.vertexWithCustomAlpha(vertexConsumer, d + (double) 1.0F, e + (double) y, w + (double) 1.0F, ac, ae, ag, ab, af, ap);
                    }

                    int aq = this.getLightColor(blockAndTintGetter, blockPos);

                    for (Direction direction : Direction.Plane.HORIZONTAL) {
                        float af;
                        float aa;
                        double ar;
                        double at;
                        double as;
                        double au;
                        boolean bl8;
                        switch (direction) {
                            case NORTH:
                                af = p;
                                aa = o;
                                ar = d;
                                as = d + (double) 1.0F;
                                at = w + (double) 0.001F;
                                au = w + (double) 0.001F;
                                bl8 = bl4;
                                break;
                            case SOUTH:
                                af = q;
                                aa = r;
                                ar = d + (double) 1.0F;
                                as = d;
                                at = w + (double) 1.0F - (double) 0.001F;
                                au = w + (double) 1.0F - (double) 0.001F;
                                bl8 = bl5;
                                break;
                            case WEST:
                                af = r;
                                aa = p;
                                ar = d + (double) 0.001F;
                                as = d + (double) 0.001F;
                                at = w + (double) 1.0F;
                                au = w;
                                bl8 = bl6;
                                break;
                            default:
                                af = o;
                                aa = q;
                                ar = d + (double) 1.0F - (double) 0.001F;
                                as = d + (double) 1.0F - (double) 0.001F;
                                at = w;
                                au = w + (double) 1.0F;
                                bl8 = bl7;
                        }

                        if (bl8 && !isFaceOccludedByNeighbor(blockAndTintGetter, blockPos, direction, Math.max(af, aa), blockAndTintGetter.getBlockState(blockPos.relative(direction)))) {
                            TextureAtlasSprite textureAtlasSprite2 = sprites[1];
                            float av = textureAtlasSprite2.getU(0.0F);
                            float aw = textureAtlasSprite2.getU(8.0F);
                            float ax = textureAtlasSprite2.getV((1.0F - af) * 16.0F * 0.5F);
                            float ay = textureAtlasSprite2.getV((1.0F - aa) * 16.0F * 0.5F);
                            float az = textureAtlasSprite2.getV(8.0F);
                            float ba = direction.getAxis() == Direction.Axis.Z ? l : m;
                            float bb = k * ba * f;
                            float bc = k * ba * g;
                            float bd = k * ba * h;
                            this.vertexWithCustomAlpha(vertexConsumer, ar, e + (double) af, at, bb, bc, bd, av, ax, aq);
                            this.vertexWithCustomAlpha(vertexConsumer, as, e + (double) aa, au, bb, bc, bd, aw, ay, aq);
                            this.vertexWithCustomAlpha(vertexConsumer, as, e + (double) y, au, bb, bc, bd, aw, az, aq);
                            this.vertexWithCustomAlpha(vertexConsumer, ar, e + (double) y, at, bb, bc, bd, av, az, aq);
                            if (textureAtlasSprite2 != this.waterOverlay) {
                                this.vertexWithCustomAlpha(vertexConsumer, ar, e + (double) y, at, bb, bc, bd, av, az, aq);
                                this.vertexWithCustomAlpha(vertexConsumer, as, e + (double) y, au, bb, bc, bd, aw, az, aq);
                                this.vertexWithCustomAlpha(vertexConsumer, as, e + (double) aa, au, bb, bc, bd, aw, ay, aq);
                                this.vertexWithCustomAlpha(vertexConsumer, ar, e + (double) af, at, bb, bc, bd, av, ax, aq);
                            }
                        }
                    }
                }
            } else {
                super.tesselate(blockAndTintGetter, blockPos, vertexConsumer, blockState, fluidState);
            }
        } catch (Exception e) {
        }
    }

    private void vertexWithCustomAlpha(VertexConsumer vertexConsumer, double x, double y, double z, float r, float g, float b, float u, float v, int packedLight) {
        vertexConsumer.vertex(x, y, z).color(r, g, b, 0.45f).uv(u, v).uv2(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
    }
}
