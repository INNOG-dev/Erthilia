package fr.karmaowner.erthilia.keystrokes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Preconditions;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;

public class KeyContainer extends GraphicObject {

	
	public UIRect editorRect = new UIRect(new UIColor(0,0,0,100));
	public UIButton moveBtn = new UIButton(Type.ROUNDED,"MoveBtn", new ResourceLocation(Main.MODID,"gui/move_btn.png"),null,false,null);
	public UIButton editBtn = new UIButton(Type.ROUNDED,"EditBtn", new ResourceLocation(Main.MODID,"gui/edit_btn.png"),null,false,null);

	private final List<Row> rows = new ArrayList<Row>();
	
	@SuppressWarnings("unused")
	private boolean dragging;
	
	private int gapSize;
	
    @SuppressWarnings("unused")
	private int displayWidth;
    @SuppressWarnings("unused")
	private int displayHeight;
	


    private double keyOffset;
	
	

    int getColor(double offset, boolean invert) {
        int color;

        if (ClientProxy.getKeyStrokesHandler().config.getChromeColor())
        {
            // if chroma effect is enabled, use black instead of the actual inverted color
            if (invert) {
                color = 0xFF000000;
            } else {
                // Cycle through color spectrum for 2 seconds
                float hue = System.currentTimeMillis() % 2000L / 2000.0f;
                // Adjust hue based on key position
                hue += (keyOffset + offset / width) * 0.3;
                color = Color.HSBtoRGB(hue, 1.0f, 1.0f);
            }
        } else {
        	this.color = new UIColor(ClientProxy.getKeyStrokesHandler().config.getKeyColor());
            color = this.color.toRGB();

            if (invert) {
                color ^= 0x00FFFFFF;
            }
        }

        return color;
    }
    
    @Override
    public void draw(int x, int y, float partialTicks)
    {
    	if(!ClientProxy.getKeyStrokesHandler().config.getIsEnabled())
    	{
    		return;
    	}
    	
         GL11.glPushMatrix();
         GL11.glScalef(getScale(), getScale(), getScale());

         for (Row row : rows) {
             GL11.glPushMatrix();

             for (Key key : row.keys) {
                 key.draw(row.keyWidth, row.height);
                 double offset = row.keyWidth + gapSize;
                 GL11.glTranslated(offset, 0.0, 0.0);
                 keyOffset += offset / width;
             }

             GL11.glPopMatrix();
             GL11.glTranslated(0.0, row.height + gapSize, 0.0);
             keyOffset = 0.0f;
         }
		 GlStateManager.color(1f, 1f, 1f, 1f);

         GL11.glPopMatrix();
    }
    
    public void drawEditor(int x, int y, float partialTicks)
    {
    	GL11.glPushMatrix();
    	
    	GL11.glScalef(getScale(), getScale(), getScale());
    	if(this.editorRect.isHover(x, y))
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 0, 10);
			this.moveBtn.draw(x, y, partialTicks);
			this.editBtn.draw(x, y, partialTicks);
			GL11.glPopMatrix();

			this.moveBtn.setPosition(this.getX2() - 5, this.posY-2, 8, 8);
			this.editBtn.setPosition(this.getX2() - 15, this.posY-2, 8, 8);
			this.editBtn.setVisible(true);
			this.moveBtn.setVisible(true);
			this.editBtn.setScale(getScale());
			this.moveBtn.setScale(getScale());
    		this.editorRect.contourColor = new UIColor(0,191,255,255);
		}
		else
		{
    		this.editorRect.contourColor = null;
			this.editBtn.setVisible(false);
			this.moveBtn.setVisible(false);
		}
    	
    	editorRect.setScale(getScale());
    	editorRect.draw(x, y, partialTicks);
    	
    	GL11.glPopMatrix();
    	
    	draw(x, y, partialTicks);
    	
    	if(editorRect.isHover(x, y))
    	{
    		Minecraft mc = Minecraft.getMinecraft();
            net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(Arrays.asList("Keystroke"), x, y, width, mc.displayHeight, -1, mc.fontRenderer);
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
    	}
    	
    }
	
    
	@Override
	public GraphicObject setPosition(int posX, int posY, int width, int height) {
		super.setPosition(posX, posY, width, height);
		this.editorRect.setPosition(posX, posY, width, height);
		return this;
	}
	
    
	public static class Builder
	{
		private KeyContainer keyContainer;
		
		public Builder(KeyContainer keyContainer)
		{
			this.keyContainer = keyContainer;
		}
		
		public Builder setWidth(int width)
		{
			Preconditions.checkArgument(width > 0);
			keyContainer.width = width;
			return this;
		}	
		
		public Builder setGapSize(int size)
		{
			 Preconditions.checkArgument(size > 0);
			 keyContainer.gapSize = size;
	         return this;
		}
		
		public Builder addRow(Key... keys) {
            Preconditions.checkState(keyContainer.width != -1 && keyContainer.gapSize != -1);

            for (Key key : keys) {
                key.setParent(keyContainer);
            }

            double keyWidth = (keyContainer.width - keyContainer.gapSize * (keys.length - 1)) / keys.length;
            double height = Arrays.stream(keys).mapToDouble(Key::getHeight).max().orElse(0.0);
            keyContainer.height += height;
            keyContainer.rows.add(new Row(keys, keyWidth, height));
            return this;
        }
		
        public KeyContainer build() {
            Preconditions.checkState(!keyContainer.rows.isEmpty());
            keyContainer.height = (int) keyContainer.rows.stream().mapToDouble(Row::getHeight).sum() + keyContainer.gapSize * (keyContainer.rows.size() - 1);
            return keyContainer;
        }
	}
	
    private static class Row {
    	
        private final Key[] keys;
        private final double keyWidth;
        private final double height;
        
        public double getHeight()
        {
        	return height;
        }

        
        public Row(Key[] keys, double keyWidth, double height) {
        	this.keys = keys;
        	this.keyWidth = keyWidth;
        	this.height = height;
        }

    }

}

