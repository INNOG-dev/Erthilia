package fr.karmaowner.erthilia.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFireAtronach extends ModelBase {
	
	private final ModelRenderer boby;
	private final ModelRenderer boby_r1;
	private final ModelRenderer boby_r2;
	private final ModelRenderer bone7;
	private final ModelRenderer bone2;
	private final ModelRenderer bone40;
	private final ModelRenderer bone27;
	private final ModelRenderer bone28;
	private final ModelRenderer cube_r1;
	private final ModelRenderer bone30;
	private final ModelRenderer cube_r2;
	private final ModelRenderer bone41;
	private final ModelRenderer bone42;
	private final ModelRenderer cube_r3;
	private final ModelRenderer bone43;
	private final ModelRenderer cube_r4;
	private final ModelRenderer arm2;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer cube_r5;
	private final ModelRenderer bone17;
	private final ModelRenderer cube_r6;
	private final ModelRenderer bone31;
	private final ModelRenderer cube_r7;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone29;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone8;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer leg1;
	private final ModelRenderer cube_r8;
	private final ModelRenderer bone32;
	private final ModelRenderer bone33;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	public final ModelRenderer head;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer cube_r9;
	private final ModelRenderer bone23;
	private final ModelRenderer cube_r10;
	private final ModelRenderer bone24;
	private final ModelRenderer cube_r11;
	private final ModelRenderer bone25;
	private final ModelRenderer cube_r12;
	private final ModelRenderer bone26;
	private final ModelRenderer cube_r13;
	private final ModelRenderer bone34;
	private final ModelRenderer bone35;
	private final ModelRenderer cube_r14;
	private final ModelRenderer bone36;
	private final ModelRenderer cube_r15;
	private final ModelRenderer bone37;
	private final ModelRenderer cube_r16;
	private final ModelRenderer bone38;
	private final ModelRenderer cube_r17;
	private final ModelRenderer bone39;
	private final ModelRenderer cube_r18;
	private final ModelRenderer leg2;
	private final ModelRenderer cube_r19;
	private final ModelRenderer bone44;
	private final ModelRenderer bone45;
	private final ModelRenderer bone46;
	private final ModelRenderer bone47;
	private final ModelRenderer arm3;
	private final ModelRenderer bone48;
	private final ModelRenderer bone49;
	private final ModelRenderer cube_r20;
	private final ModelRenderer bone50;
	private final ModelRenderer cube_r21;
	private final ModelRenderer bone51;
	private final ModelRenderer cube_r22;
	private final ModelRenderer bone52;
	private final ModelRenderer bone53;
	private final ModelRenderer bone54;
	private final ModelRenderer bone55;
	private final ModelRenderer bone56;
	private final ModelRenderer bone57;
	private final ModelRenderer bone58;
	private final ModelRenderer bone59;
	private final ModelRenderer bone60;
	private final ModelRenderer bone61;
	private final ModelRenderer bone62;
	private final ModelRenderer bone63;
		
	public ModelFireAtronach() {	
		textureWidth = 256;
		textureHeight = 256;

		boby = new ModelRenderer(this);
		boby.setRotationPoint(0.0F, -17.0F, 0.0F);
		boby.cubeList.add(new ModelBox(boby, 45, 234, -8.0F, -28.0F, -2.0F, 16, 14, 7, 0.0F, false));
		boby.cubeList.add(new ModelBox(boby, 54, 223, -4.0F, -32.0F, -2.0F, 8, 4, 7, 0.0F, false));
		boby.cubeList.add(new ModelBox(boby, 0, 139, -5.5F, -14.0F, -2.0F, 11, 15, 7, 0.0F, false));

		boby_r1 = new ModelRenderer(this);
		boby_r1.setRotationPoint(-6.0F, -11.0F, 1.5F);
		boby.addChild(boby_r1);
		setRotationAngle(boby_r1, 0.0F, 0.0F, -0.3054F);
		boby_r1.cubeList.add(new ModelBox(boby_r1, 23, 239, -1.0F, -3.5F, -3.5F, 3, 9, 7, -0.01F, true));

		boby_r2 = new ModelRenderer(this);
		boby_r2.setRotationPoint(6.0F, -11.0F, 1.5F);
		boby.addChild(boby_r2);
		setRotationAngle(boby_r2, 0.0F, 0.0F, 0.3054F);
		boby_r2.cubeList.add(new ModelBox(boby_r2, 23, 239, -2.0F, -3.5F, -3.5F, 3, 9, 7, -0.01F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, -0.5F, 1.6F);
		boby.addChild(bone7);
		setRotationAngle(bone7, 0.3491F, 0.0F, 0.0F);
		bone7.cubeList.add(new ModelBox(bone7, 0, 161, -6.5F, -2.5F, -4.0F, 13, 7, 9, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(4.5F, -4.7F, -5.0F);
		boby.addChild(bone2);
		setRotationAngle(bone2, 0.3927F, -0.3927F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 127, -3.5F, -16.4343F, 5.3576F, 7, 7, 5, 0.0F, true));
		bone2.cubeList.add(new ModelBox(bone2, 35, 123, 3.5F, -16.4343F, 5.3576F, 5, 7, 0, 0.0F, true));

		bone40 = new ModelRenderer(this);
		bone40.setRotationPoint(-4.5F, -4.7F, -5.0F);
		boby.addChild(bone40);
		setRotationAngle(bone40, 0.3927F, 0.3927F, 0.0F);
		bone40.cubeList.add(new ModelBox(bone40, 0, 127, -3.5F, -16.4343F, 5.3576F, 7, 7, 5, 0.0F, false));
		bone40.cubeList.add(new ModelBox(bone40, 35, 123, -8.5F, -16.4343F, 5.3576F, 5, 7, 0, 0.0F, false));

		bone27 = new ModelRenderer(this);
		bone27.setRotationPoint(-7.1101F, -28.1788F, 2.0F);
		boby.addChild(bone27);
		setRotationAngle(bone27, 0.0F, 0.0F, 0.7418F);
		bone27.cubeList.add(new ModelBox(bone27, 53, 174, -4.0F, -1.0F, -3.0F, 8, 2, 6, -0.01F, true));

		bone28 = new ModelRenderer(this);
		bone28.setRotationPoint(-2.541F, 18.6441F, -4.0F);
		bone27.addChild(bone28);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-1.2509F, -18.2918F, 4.0F);
		bone28.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.48F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 57, 182, -3.8546F, -1.423F, -3.0F, 4, 2, 6, -0.02F, true));

		bone30 = new ModelRenderer(this);
		bone30.setRotationPoint(-6.91F, -20.1091F, 4.0F);
		bone28.addChild(bone30);
		setRotationAngle(bone30, 0.0F, 0.0F, 0.4363F);
		

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(4.8546F, 0.423F, 0.0F);
		bone30.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.48F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 62, 190, -6.8546F, -1.423F, -2.0F, 4, 2, 4, -0.02F, true));

		bone41 = new ModelRenderer(this);
		bone41.setRotationPoint(7.1101F, -28.1788F, 2.0F);
		boby.addChild(bone41);
		setRotationAngle(bone41, 0.0F, 0.0F, -0.7418F);
		bone41.cubeList.add(new ModelBox(bone41, 53, 174, -4.0F, -1.0F, -3.0F, 8, 2, 6, -0.01F, false));

		bone42 = new ModelRenderer(this);
		bone42.setRotationPoint(2.541F, 18.6441F, -4.0F);
		bone41.addChild(bone42);
		

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(1.2509F, -18.2918F, 4.0F);
		bone42.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.48F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 57, 182, -0.1454F, -1.423F, -3.0F, 4, 2, 6, -0.02F, false));

		bone43 = new ModelRenderer(this);
		bone43.setRotationPoint(6.91F, -20.1091F, 4.0F);
		bone42.addChild(bone43);
		setRotationAngle(bone43, 0.0F, 0.0F, -0.4363F);
		

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-4.8546F, 0.423F, 0.0F);
		bone43.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, -0.48F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 62, 190, 2.8546F, -1.423F, -2.0F, 4, 2, 4, -0.02F, false));

		arm2 = new ModelRenderer(this);
		arm2.setRotationPoint(-8.3F, -40.7F, 1.0F);
		setRotationAngle(arm2, 0.0F, 0.0F, -0.48F);
		arm2.cubeList.add(new ModelBox(arm2, 23, 227, -5.6913F, -3.4351F, -2.5F, 8, 6, 6, 0.0F, true));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(-1.6913F, -5.4351F, 1.0F);
		arm2.addChild(bone13);
		setRotationAngle(bone13, 0.0F, 0.0F, 0.7854F);
		bone13.cubeList.add(new ModelBox(bone13, 53, 174, -4.0F, -1.0F, -3.5F, 8, 2, 6, -0.01F, true));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(-2.541F, 18.6441F, -4.0F);
		bone13.addChild(bone14);
		

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-1.2509F, -18.2918F, 4.0F);
		bone14.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.48F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 57, 182, -3.8546F, -1.423F, -3.5F, 4, 2, 6, -0.02F, true));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(-4.9816F, -17.8109F, 4.0F);
		bone14.addChild(bone17);
		setRotationAngle(bone17, 0.0F, 0.0F, -0.6545F);
		

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(4.8546F, 0.423F, 0.0F);
		bone17.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.48F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 62, 190, -6.8546F, -1.423F, -2.5F, 4, 2, 4, -0.02F, true));

		bone31 = new ModelRenderer(this);
		bone31.setRotationPoint(-1.5121F, -0.2311F, 0.0F);
		bone17.addChild(bone31);
		setRotationAngle(bone31, 0.0F, 0.0F, -0.6981F);
		

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(4.8546F, 0.423F, 1.0F);
		bone31.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.48F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 39, 186, -6.8546F, -1.423F, -2.5F, 4, 2, 2, -0.02F, true));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(-5.2758F, -5.0463F, 1.0F);
		arm2.addChild(bone15);
		setRotationAngle(bone15, 0.0F, 0.0F, 0.7854F);
		bone15.cubeList.add(new ModelBox(bone15, 61, 202, -2.0F, -1.0F, -3.5F, 6, 2, 6, -0.01F, true));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(-1.4624F, 0.1439F, 0.0F);
		bone15.addChild(bone16);
		setRotationAngle(bone16, 0.0F, 0.0F, 0.3927F);
		bone16.cubeList.add(new ModelBox(bone16, 66, 196, -3.1561F, -1.2642F, -2.5F, 3, 2, 4, -0.01F, true));

		bone29 = new ModelRenderer(this);
		bone29.setRotationPoint(-10.7881F, 8.9114F, -0.4F);
		arm2.addChild(bone29);
		setRotationAngle(bone29, 0.0F, -0.6545F, 0.0F);
		bone29.cubeList.add(new ModelBox(bone29, 30, 205, -6.3066F, -11.9735F, -5.2607F, 7, 5, 5, -0.01F, true));
		bone29.cubeList.add(new ModelBox(bone29, 64, 219, -11.3066F, -11.9735F, -4.2607F, 5, 1, 3, -0.01F, true));
		bone29.cubeList.add(new ModelBox(bone29, 27, 215, -0.4645F, -12.3283F, -5.7698F, 7, 6, 6, -0.01F, true));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(-11.0034F, -11.352F, -3.6402F);
		bone29.addChild(bone18);
		setRotationAngle(bone18, 0.0F, 0.0F, 0.6981F);
		bone18.cubeList.add(new ModelBox(bone18, 40, 202, -2.9626F, -0.4307F, -0.1313F, 3, 1, 2, -0.01F, true));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(-2.339F, -0.5276F, 0.7502F);
		bone18.addChild(bone19);
		setRotationAngle(bone19, 0.0F, 0.0F, 0.48F);
		bone19.cubeList.add(new ModelBox(bone19, 0, 196, -1.9524F, -0.1202F, -0.3567F, 2, 1, 1, -0.01F, true));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-8.1601F, -8.8283F, -2.3731F);
		bone29.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.3927F);
		bone8.cubeList.add(new ModelBox(bone8, 36, 191, -1.2631F, -1.595F, -3.41F, 4, 5, 6, -0.01F, true));
		bone8.cubeList.add(new ModelBox(bone8, 67, 210, -3.2631F, -1.595F, -3.41F, 2, 3, 6, -0.01F, true));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-1.9278F, -0.8528F, 2.0605F);
		bone8.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.5672F);
		bone5.cubeList.add(new ModelBox(bone5, 0, 123, -4.7069F, -1.281F, -1.7213F, 4, 2, 2, -0.01F, true));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-5.0653F, 0.3196F, -0.2588F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.3927F);
		bone6.cubeList.add(new ModelBox(bone6, 0, 119, -1.0659F, -1.2972F, -0.8967F, 2, 2, 1, -0.01F, true));
		bone6.cubeList.add(new ModelBox(bone6, 0, 112, -4.0659F, -1.2972F, -0.8967F, 3, 1, 1, -0.01F, true));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-1.9278F, -0.8528F, 2.0605F);
		bone8.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, -0.1309F);
		bone9.cubeList.add(new ModelBox(bone9, 0, 123, -5.1309F, -0.8526F, -5.1632F, 4, 2, 2, -0.01F, true));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-5.0653F, 0.3196F, -0.2588F);
		bone9.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 0.0F, -0.3927F);
		bone10.cubeList.add(new ModelBox(bone10, 0, 119, -1.6215F, -1.0636F, -4.3386F, 2, 2, 1, -0.01F, true));
		bone10.cubeList.add(new ModelBox(bone10, 0, 112, -4.6215F, -1.0636F, -4.3386F, 3, 1, 1, -0.01F, true));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-0.849F, -1.2108F, -3.0108F);
		bone8.addChild(bone11);
		setRotationAngle(bone11, 1.789F, 0.0F, -0.5672F);
		bone11.cubeList.add(new ModelBox(bone11, 0, 123, -4.7669F, -1.3871F, -4.7364F, 4, 2, 2, -0.01F, true));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-5.0653F, 0.3196F, -0.2588F);
		bone11.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, -0.3927F);
		bone12.cubeList.add(new ModelBox(bone12, 0, 119, -1.0807F, -1.4181F, -3.9118F, 2, 2, 1, -0.01F, true));
		bone12.cubeList.add(new ModelBox(bone12, 0, 112, -4.0807F, -1.4181F, -3.9118F, 3, 1, 1, -0.01F, true));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(3.6667F, -16.9167F, 1.0F);
		setRotationAngle(leg1, -0.2618F, 0.0F, 0.0436F);
		leg1.cubeList.add(new ModelBox(leg1, 0, 191, -3.6667F, -1.0493F, -3.7588F, 8, 10, 8, 0.0F, false));
		leg1.cubeList.add(new ModelBox(leg1, 0, 209, -3.1667F, 8.9507F, -3.2588F, 7, 6, 7, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.3333F, 3.9507F, 5.2412F);
		leg1.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.3054F, 0.0F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 0, 177, -4.0783F, -4.3908F, -2.8491F, 8, 10, 4, -0.01F, false));

		bone32 = new ModelRenderer(this);
		bone32.setRotationPoint(4.368F, 11.6331F, 0.1561F);
		leg1.addChild(bone32);
		setRotationAngle(bone32, 0.0F, 0.0F, 0.6109F);
		bone32.cubeList.add(new ModelBox(bone32, 0, 28, -1.5F, -3.0F, -1.5F, 3, 6, 3, 0.0F, false));

		bone33 = new ModelRenderer(this);
		bone33.setRotationPoint(1.2216F, -4.9516F, 0.4468F);
		bone32.addChild(bone33);
		setRotationAngle(bone33, 0.0F, 0.0F, 0.5236F);
		bone33.cubeList.add(new ModelBox(bone33, 16, 117, -0.5F, -3.0F, -1.5F, 2, 6, 2, -0.01F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.3333F, 24.9597F, 2.3143F);
		leg1.addChild(bone3);
		setRotationAngle(bone3, 0.5236F, 0.0F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, 0, 222, -2.5F, -11.4458F, 0.9328F, 5, 14, 5, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 4.827F, 2.7258F);
		bone3.addChild(bone4);
		setRotationAngle(bone4, -0.4363F, 0.0F, 0.0F);
		bone4.cubeList.add(new ModelBox(bone4, 0, 242, -2.5F, -4.5F, -2.0F, 5, 9, 4, 0.01F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -49.0F, 1.5F);
		

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(0.0F, -7.5F, 0.0F);
		head.addChild(bone20);
		setRotationAngle(bone20, 0.0F, -0.7854F, 0.0F);
		bone20.cubeList.add(new ModelBox(bone20, 0, 83, -6.0F, -8.5F, -6.0F, 12, 17, 12, 0.0F, false));
		bone20.cubeList.add(new ModelBox(bone20, 189, 0, -4.0503F, -22.5F, -3.8787F, 17, 24, 17, 0.0F, false));

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(4.0F, -12.4F, 3.8F);
		head.addChild(bone21);
		setRotationAngle(bone21, 0.5672F, -0.6109F, -0.48F);
		bone21.cubeList.add(new ModelBox(bone21, 0, 71, -1.0F, -2.5F, -4.0F, 8, 6, 6, 0.0F, false));

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(5.469F, 0.9265F, -1.3F);
		bone21.addChild(bone22);
		setRotationAngle(bone22, 0.0F, 0.3054F, 0.5672F);
		

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(-0.0152F, -0.3486F, 1.0F);
		bone22.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 0.3927F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 0, 61, -1.8093F, -2.2552F, -3.5F, 8, 5, 5, -0.01F, false));

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(3.4986F, 0.7324F, 0.5163F);
		bone22.addChild(bone23);
		setRotationAngle(bone23, 0.0F, 0.3054F, 0.4363F);
		

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(-4.8803F, -3.0859F, 1.0F);
		bone23.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.3927F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 0, 53, 6.1907F, -1.2552F, -3.0F, 5, 4, 4, -0.01F, false));

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(3.4428F, -3.0721F, 0.0F);
		bone23.addChild(bone24);
		setRotationAngle(bone24, 0.0F, 0.0F, -0.7418F);
		

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(-13.1907F, -1.2448F, 0.5F);
		bone24.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, 0.3927F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 0, 47, 11.1907F, -0.2552F, -2.0F, 6, 3, 3, -0.01F, false));

		bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(0.7186F, 1.2692F, -0.5F);
		bone24.addChild(bone25);
		setRotationAngle(bone25, 0.0F, 0.0F, -0.8727F);
		

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(-14.1907F, -1.2448F, 0.5F);
		bone25.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, 0.3927F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 0, 42, 11.1907F, -0.2552F, -1.0F, 5, 2, 2, -0.01F, false));

		bone26 = new ModelRenderer(this);
		bone26.setRotationPoint(-0.5F, 0.0F, 0.5F);
		bone25.addChild(bone26);
		setRotationAngle(bone26, 0.0F, 0.0F, -0.48F);
		

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(-13.6907F, -1.2448F, 0.5F);
		bone26.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, 0.0F, 0.3927F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 0, 39, 13.1907F, 0.7448F, -1.0F, 3, 1, 1, -0.01F, false));

		bone34 = new ModelRenderer(this);
		bone34.setRotationPoint(-4.0F, -12.4F, 3.8F);
		head.addChild(bone34);
		setRotationAngle(bone34, 0.5672F, 0.6109F, 0.48F);
		bone34.cubeList.add(new ModelBox(bone34, 0, 71, -7.0F, -2.5F, -4.0F, 8, 6, 6, 0.0F, true));

		bone35 = new ModelRenderer(this);
		bone35.setRotationPoint(-5.469F, 0.9265F, -1.3F);
		bone34.addChild(bone35);
		setRotationAngle(bone35, 0.0F, -0.3054F, -0.5672F);
		

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(0.0152F, -0.3486F, 1.0F);
		bone35.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, 0.0F, -0.3927F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 0, 61, -6.1907F, -2.2552F, -3.5F, 8, 5, 5, -0.01F, true));

		bone36 = new ModelRenderer(this);
		bone36.setRotationPoint(-3.4986F, 0.7324F, 0.5163F);
		bone35.addChild(bone36);
		setRotationAngle(bone36, 0.0F, -0.3054F, -0.4363F);
		

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(4.8803F, -3.0859F, 1.0F);
		bone36.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, 0.0F, -0.3927F);
		cube_r15.cubeList.add(new ModelBox(cube_r15, 0, 53, -11.1907F, -1.2552F, -3.0F, 5, 4, 4, -0.01F, true));

		bone37 = new ModelRenderer(this);
		bone37.setRotationPoint(-3.4428F, -3.0721F, 0.0F);
		bone36.addChild(bone37);
		setRotationAngle(bone37, 0.0F, 0.0F, 0.7418F);
		

		cube_r16 = new ModelRenderer(this);
		cube_r16.setRotationPoint(13.1907F, -1.2448F, 0.5F);
		bone37.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.0F, -0.3927F);
		cube_r16.cubeList.add(new ModelBox(cube_r16, 0, 47, -17.1907F, -0.2552F, -2.0F, 6, 3, 3, -0.01F, true));

		bone38 = new ModelRenderer(this);
		bone38.setRotationPoint(-0.7186F, 1.2692F, -0.5F);
		bone37.addChild(bone38);
		setRotationAngle(bone38, 0.0F, 0.0F, 0.8727F);
		

		cube_r17 = new ModelRenderer(this);
		cube_r17.setRotationPoint(14.1907F, -1.2448F, 0.5F);
		bone38.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.0F, -0.3927F);
		cube_r17.cubeList.add(new ModelBox(cube_r17, 0, 42, -16.1907F, -0.2552F, -1.0F, 5, 2, 2, -0.01F, true));

		bone39 = new ModelRenderer(this);
		bone39.setRotationPoint(0.5F, 0.0F, 0.5F);
		bone38.addChild(bone39);
		setRotationAngle(bone39, 0.0F, 0.0F, 0.48F);
		

		cube_r18 = new ModelRenderer(this);
		cube_r18.setRotationPoint(13.6907F, -1.2448F, 0.5F);
		bone39.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, 0.0F, -0.3927F);
		cube_r18.cubeList.add(new ModelBox(cube_r18, 0, 39, -16.1907F, 0.7448F, -1.0F, 3, 1, 1, -0.01F, true));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(-3.6667F, -16.9167F, 1.0F);
		setRotationAngle(leg2, -0.2618F, 0.0F, -0.0436F);
		leg2.cubeList.add(new ModelBox(leg2, 0, 191, -4.3333F, -1.0493F, -3.7588F, 8, 10, 8, 0.0F, true));
		leg2.cubeList.add(new ModelBox(leg2, 0, 209, -3.8333F, 8.9507F, -3.2588F, 7, 6, 7, 0.0F, true));

		cube_r19 = new ModelRenderer(this);
		cube_r19.setRotationPoint(-0.3333F, 3.9507F, 5.2412F);
		leg2.addChild(cube_r19);
		setRotationAngle(cube_r19, -0.3054F, 0.0F, 0.0F);
		cube_r19.cubeList.add(new ModelBox(cube_r19, 0, 177, -3.9217F, -4.3908F, -2.8491F, 8, 10, 4, -0.01F, true));

		bone44 = new ModelRenderer(this);
		bone44.setRotationPoint(-4.368F, 11.6331F, 0.1561F);
		leg2.addChild(bone44);
		setRotationAngle(bone44, 0.0F, 0.0F, -0.6109F);
		bone44.cubeList.add(new ModelBox(bone44, 0, 28, -1.5F, -3.0F, -1.5F, 3, 6, 3, 0.0F, true));

		bone45 = new ModelRenderer(this);
		bone45.setRotationPoint(-1.2216F, -4.9516F, 0.4468F);
		bone44.addChild(bone45);
		setRotationAngle(bone45, 0.0F, 0.0F, -0.5236F);
		bone45.cubeList.add(new ModelBox(bone45, 16, 117, -1.5F, -3.0F, -1.5F, 2, 6, 2, -0.01F, true));

		bone46 = new ModelRenderer(this);
		bone46.setRotationPoint(-0.3333F, 24.9597F, 2.3143F);
		leg2.addChild(bone46);
		setRotationAngle(bone46, 0.5236F, 0.0F, 0.0F);
		bone46.cubeList.add(new ModelBox(bone46, 0, 222, -2.5F, -11.4458F, 0.9328F, 5, 14, 5, 0.0F, true));

		bone47 = new ModelRenderer(this);
		bone47.setRotationPoint(0.0F, 4.827F, 2.7258F);
		bone46.addChild(bone47);
		setRotationAngle(bone47, -0.4363F, 0.0F, 0.0F);
		bone47.cubeList.add(new ModelBox(bone47, 0, 242, -2.5F, -4.5F, -2.0F, 5, 9, 4, 0.01F, true));

		arm3 = new ModelRenderer(this);
		arm3.setRotationPoint(8.3F, -40.7F, 1.0F);
		setRotationAngle(arm3, 0.0F, 0.0F, 0.48F);
		arm3.cubeList.add(new ModelBox(arm3, 23, 227, -2.3087F, -3.4351F, -2.5F, 8, 6, 6, 0.0F, false));

		bone48 = new ModelRenderer(this);
		bone48.setRotationPoint(1.6913F, -5.4351F, 1.0F);
		arm3.addChild(bone48);
		setRotationAngle(bone48, 0.0F, 0.0F, -0.7854F);
		bone48.cubeList.add(new ModelBox(bone48, 53, 174, -4.0F, -1.0F, -3.5F, 8, 2, 6, -0.01F, false));

		bone49 = new ModelRenderer(this);
		bone49.setRotationPoint(2.541F, 18.6441F, -4.0F);
		bone48.addChild(bone49);
		

		cube_r20 = new ModelRenderer(this);
		cube_r20.setRotationPoint(1.2509F, -18.2918F, 4.0F);
		bone49.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0F, 0.0F, -0.48F);
		cube_r20.cubeList.add(new ModelBox(cube_r20, 57, 182, -0.1454F, -1.423F, -3.5F, 4, 2, 6, -0.02F, false));

		bone50 = new ModelRenderer(this);
		bone50.setRotationPoint(4.9816F, -17.8109F, 4.0F);
		bone49.addChild(bone50);
		setRotationAngle(bone50, 0.0F, 0.0F, 0.6545F);
		

		cube_r21 = new ModelRenderer(this);
		cube_r21.setRotationPoint(-4.8546F, 0.423F, 0.0F);
		bone50.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.0F, 0.0F, -0.48F);
		cube_r21.cubeList.add(new ModelBox(cube_r21, 62, 190, 2.8546F, -1.423F, -2.5F, 4, 2, 4, -0.02F, false));

		bone51 = new ModelRenderer(this);
		bone51.setRotationPoint(1.5121F, -0.2311F, 0.0F);
		bone50.addChild(bone51);
		setRotationAngle(bone51, 0.0F, 0.0F, 0.6981F);
		

		cube_r22 = new ModelRenderer(this);
		cube_r22.setRotationPoint(-4.8546F, 0.423F, 1.0F);
		bone51.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0F, 0.0F, -0.48F);
		cube_r22.cubeList.add(new ModelBox(cube_r22, 39, 186, 2.8546F, -1.423F, -2.5F, 4, 2, 2, -0.02F, false));

		bone52 = new ModelRenderer(this);
		bone52.setRotationPoint(5.2758F, -5.0463F, 1.0F);
		arm3.addChild(bone52);
		setRotationAngle(bone52, 0.0F, 0.0F, -0.7854F);
		bone52.cubeList.add(new ModelBox(bone52, 61, 202, -4.0F, -1.0F, -3.5F, 6, 2, 6, -0.01F, false));

		bone53 = new ModelRenderer(this);
		bone53.setRotationPoint(1.4624F, 0.1439F, 0.0F);
		bone52.addChild(bone53);
		setRotationAngle(bone53, 0.0F, 0.0F, -0.3927F);
		bone53.cubeList.add(new ModelBox(bone53, 66, 196, 0.1561F, -1.2642F, -2.5F, 3, 2, 4, -0.01F, false));

		bone54 = new ModelRenderer(this);
		bone54.setRotationPoint(10.7881F, 8.9114F, -0.4F);
		arm3.addChild(bone54);
		setRotationAngle(bone54, 0.0F, 0.6545F, 0.0F);
		bone54.cubeList.add(new ModelBox(bone54, 30, 205, -0.6934F, -11.9735F, -5.2607F, 7, 5, 5, -0.01F, false));
		bone54.cubeList.add(new ModelBox(bone54, 64, 219, 6.3066F, -11.9735F, -4.2607F, 5, 1, 3, -0.01F, false));
		bone54.cubeList.add(new ModelBox(bone54, 27, 215, -6.5355F, -12.3283F, -5.7698F, 7, 6, 6, -0.01F, false));

		bone55 = new ModelRenderer(this);
		bone55.setRotationPoint(11.0034F, -11.352F, -3.6402F);
		bone54.addChild(bone55);
		setRotationAngle(bone55, 0.0F, 0.0F, -0.6981F);
		bone55.cubeList.add(new ModelBox(bone55, 40, 202, -0.0374F, -0.4307F, -0.1313F, 3, 1, 2, -0.01F, false));

		bone56 = new ModelRenderer(this);
		bone56.setRotationPoint(2.339F, -0.5276F, 0.7502F);
		bone55.addChild(bone56);
		setRotationAngle(bone56, 0.0F, 0.0F, -0.48F);
		bone56.cubeList.add(new ModelBox(bone56, 0, 196, -0.0476F, -0.1202F, -0.3567F, 2, 1, 1, -0.01F, false));

		bone57 = new ModelRenderer(this);
		bone57.setRotationPoint(8.1601F, -8.8283F, -2.3731F);
		bone54.addChild(bone57);
		setRotationAngle(bone57, 0.0F, 0.0F, 0.3927F);
		bone57.cubeList.add(new ModelBox(bone57, 36, 191, -2.7369F, -1.595F, -3.41F, 4, 5, 6, -0.01F, false));
		bone57.cubeList.add(new ModelBox(bone57, 67, 210, 1.2631F, -1.595F, -3.41F, 2, 3, 6, -0.01F, false));

		bone58 = new ModelRenderer(this);
		bone58.setRotationPoint(1.9278F, -0.8528F, 2.0605F);
		bone57.addChild(bone58);
		setRotationAngle(bone58, 0.0F, 0.0F, 0.5672F);
		bone58.cubeList.add(new ModelBox(bone58, 0, 123, 0.7069F, -1.281F, -1.7213F, 4, 2, 2, -0.01F, false));

		bone59 = new ModelRenderer(this);
		bone59.setRotationPoint(5.0653F, 0.3196F, -0.2588F);
		bone58.addChild(bone59);
		setRotationAngle(bone59, 0.0F, 0.0F, 0.3927F);
		bone59.cubeList.add(new ModelBox(bone59, 0, 119, -0.9341F, -1.2972F, -0.8967F, 2, 2, 1, -0.01F, false));
		bone59.cubeList.add(new ModelBox(bone59, 0, 112, 1.0659F, -1.2972F, -0.8967F, 3, 1, 1, -0.01F, false));

		bone60 = new ModelRenderer(this);
		bone60.setRotationPoint(1.9278F, -0.8528F, 2.0605F);
		bone57.addChild(bone60);
		setRotationAngle(bone60, 0.0F, 0.0F, 0.1309F);
		bone60.cubeList.add(new ModelBox(bone60, 0, 123, 1.1309F, -0.8526F, -5.1632F, 4, 2, 2, -0.01F, false));

		bone61 = new ModelRenderer(this);
		bone61.setRotationPoint(5.0653F, 0.3196F, -0.2588F);
		bone60.addChild(bone61);
		setRotationAngle(bone61, 0.0F, 0.0F, 0.3927F);
		bone61.cubeList.add(new ModelBox(bone61, 0, 119, -0.3785F, -1.0636F, -4.3386F, 2, 2, 1, -0.01F, false));
		bone61.cubeList.add(new ModelBox(bone61, 0, 112, 1.6215F, -1.0636F, -4.3386F, 3, 1, 1, -0.01F, false));

		bone62 = new ModelRenderer(this);
		bone62.setRotationPoint(0.849F, -1.2108F, -3.0108F);
		bone57.addChild(bone62);
		setRotationAngle(bone62, 1.789F, 0.0F, 0.5672F);
		bone62.cubeList.add(new ModelBox(bone62, 0, 123, 0.7669F, -1.3871F, -4.7364F, 4, 2, 2, -0.01F, false));

		bone63 = new ModelRenderer(this);
		bone63.setRotationPoint(5.0653F, 0.3196F, -0.2588F);
		bone62.addChild(bone63);
		setRotationAngle(bone63, 0.0F, 0.0F, 0.3927F);
		bone63.cubeList.add(new ModelBox(bone63, 0, 119, -0.9193F, -1.4181F, -3.9118F, 2, 2, 1, -0.01F, false));
		bone63.cubeList.add(new ModelBox(bone63, 0, 112, 1.0807F, -1.4181F, -3.9118F, 3, 1, 1, -0.01F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		
		boby.render(f5);
		arm2.render(f5);
		leg1.render(f5);
		head.render(f5);
		leg2.render(f5);
		arm3.render(f5);
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{

	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	

}
