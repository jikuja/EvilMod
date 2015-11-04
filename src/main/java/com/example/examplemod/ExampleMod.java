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