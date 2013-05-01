package net.minecraft.src;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
/*//FMLdelete
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
*///FMLdelete
public class ItemRidingIndicatingRod extends Item {
	private EntityLiving setEntity;
	private Minecraft minecraft = Minecraft.getMinecraft();

	public ItemRidingIndicatingRod(int par1) {
		super(par1);
		this.setMaxStackSize(1);
		this.setMaxDamage(30);
	}

	@Override
	public int getColorFromItemStack(ItemStack itemstack, int par1) {
		return 0x22ff008c;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityLiving entity) {
		if (entity.worldObj.isRemote) {
			mod_RidingIndicatingRod.mDebug("isRemote");
			return true;
		}
		if(setEntity == null) {
			setEntity = entity;
			mod_RidingIndicatingRod.mDebug("setEntity");
			if (mod_RidingIndicatingRod.useAddChatMessage) minecraft.ingameGUI.getChatGUI().printChatMessage("RidingIndicatingRod setEntity.");
			return true;
		}
		if(setEntity == entity) {
			mod_RidingIndicatingRod.mDebug("setEntity == entity get off.");
			if (mod_RidingIndicatingRod.useAddChatMessage) minecraft.ingameGUI.getChatGUI().printChatMessage("RidingIndicatingRod setEntity == entity get off.");
			if (entity != null) entity.mountEntity(null);
			if (entity.riddenByEntity != null) entity.riddenByEntity.mountEntity(null);
			return true;
		}
		if (setEntity.isDead) {
			setEntity = null;
			mod_RidingIndicatingRod.mDebug("setEntity.isDead");
			if (mod_RidingIndicatingRod.useAddChatMessage) minecraft.ingameGUI.getChatGUI().printChatMessage("RidingIndicatingRod setEntity.isDead");
			return true;
		}
		if (entity.isDead) {
			mod_RidingIndicatingRod.mDebug("entity.isDead");
			if (mod_RidingIndicatingRod.useAddChatMessage) minecraft.ingameGUI.getChatGUI().printChatMessage("RidingIndicatingRod entity.isDead");
			return true;
		}
		mod_RidingIndicatingRod.mDebug("riding set.");
		if (mod_RidingIndicatingRod.useAddChatMessage) minecraft.ingameGUI.getChatGUI().printChatMessage("RidingIndicatingRod riding set.");
		setEntity.mountEntity(entity);
		setEntity = null;
		itemstack.damageItem(1, entity);
		return true;
	}

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
		if (par3EntityPlayer.worldObj.isRemote) return;
		int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
    	float var7 = (float)var6 / 20.0F;
    	var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
    	if ((double)var7 < 0.1D)
    	{
    		return;
    	}
    	if (var7 > 1.0F)
    	{
    		var7 = 1.0F;
    	}
    	if (var7 == 1.0F)
    	{
    		if (mod_RidingIndicatingRod.useAddChatMessage) minecraft.ingameGUI.getChatGUI().printChatMessage("RidingIndicatingRod setEntity reset.");
    		setEntity = null;
    	}
    }

	@Override
	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
	@Override
	public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3)
    {
        var2.heal(1);
        return this.itemInteractionForEntity(var1, var2);
    }

/*
	public String getClassName(String s) {
		if (s == null) return null;
		Package pac = getClass().getPackage();
		if (pac != null) s = pac.getName().concat(".").concat(s);
		return s;
	}

	public boolean whiteListCheck(EntityLiving entity) {
		boolean flag = false;
		for (int i = 0 ; i < mod_RidingIndicatingRod.whiteList.size() && !flag ; i++) {
			String s = (String)mod_RidingIndicatingRod.whiteList.get(i);
			try {
				Class c = Class.forName(getClassName(s));
				if (c.isInstance(entity)) flag = true;
			} catch (ClassNotFoundException e) {
				if (entity.texture.indexOf(s.concat(".")) != -1) flag = true;
			}
		}
		return flag;
	}

	public boolean ngListCheck(EntityLiving entity) {
		boolean flag = false;
		for (int i = 0 ; i < mod_RidingIndicatingRod.ngList.size() && !flag ; i++) {
			String s = (String)mod_RidingIndicatingRod.ngList.get(i);
			try {
				Class c = Class.forName(getClassName(s));
				if (c.isInstance(entity)) flag = true;
			} catch (ClassNotFoundException e) {
				if (entity.texture.indexOf(s.concat(".")) != -1) flag = true;
			}
		}
		return flag;
	}
*/
}