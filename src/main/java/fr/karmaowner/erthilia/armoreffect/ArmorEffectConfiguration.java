package fr.karmaowner.erthilia.armoreffect;

import com.google.common.base.Preconditions;

import fr.karmaowner.erthilia.utils.MathsUtils;

public class ArmorEffectConfiguration {
	
		
		private boolean isEnabled;
		
		private int opacity;
		
		private int currentTheme = 1;
		
		public boolean getIsEnabled()
		{
			return this.isEnabled;
		}
		
		public void setEnabled(boolean state)
		{
			this.isEnabled = state;
		}
		
		public int getOpacity()
		{
			return this.opacity;
		}
		
		public void setOpacity(int opacity)
		{
			Preconditions.checkArgument(opacity > 0 || opacity < 255);
			this.opacity = opacity;
		}
		
		
		public int getCurrentTheme()
		{
			return this.currentTheme;
		}
		
		public void setCurrentTheme(int theme)
		{
			if(theme > 3)
			{
				this.currentTheme = 1;
				return;
			}
			this.currentTheme = (int)MathsUtils.Clamp(theme, 1, getThemeCount());
		}
		
		public int getThemeCount()
		{
			return 3;
		}
		
}
	

