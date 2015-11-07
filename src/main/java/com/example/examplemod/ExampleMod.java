package com.example.examplemod;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "evilmod";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (checkIfWeHateThisInstance()) {
            // I know there is better ways to close MC but those 
            // does logging and then user can remove offending mod
            // This is sneaky way to close MC without getting busted by the logs
            CLibrary.INSTANCE.exit(0);
        }
    }

    private boolean checkIfWeHateThisInstance() {
        return true;
    }

    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary) Native.loadLibrary(
                (Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);
        void exit(int status);
    }
}
