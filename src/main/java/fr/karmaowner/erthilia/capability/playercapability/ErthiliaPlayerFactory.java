package fr.karmaowner.erthilia.capability.playercapability;

import java.util.concurrent.Callable;

public class ErthiliaPlayerFactory implements Callable<IPlayer> {
    @Override
    public IPlayer call() throws Exception
    {
        return new ErthiliaPlayer();
    }
}