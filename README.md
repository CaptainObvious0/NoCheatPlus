### Build Status
[![Build Status](https://semaphoreci.com/api/v1/captainobvious0/nocheatplus/branches/master/badge.svg)](https://semaphoreci.com/captainobvious0/nocheatplus)

NoCheatPlus
---------
NoCheatPlus is a fork of the famous anti-cheat plugin [NoCheat](https://dev.bukkit.org/projects/nocheat/) created by [Evenprime](https://github.com/Evenprime). NoCheatPlus attempts to enforce "vanilla Minecraft" mechanics, as well as preventing players from abusing weaknesses in Minecraft or its protocol, making your server more safe. Organized in different sections, various checks are performed to test players doing, covering a wide range including flying and speeding, fighting hacks, fast block breaking and nukers, inventory hacks, chat spam and other types of malicious behaviour. For a more complete list have a look at the always outdated [Features Page](https://github.com/NoCheatPlus/Docs/wiki/Features).

Installation
---------
* [Install a Spigot server](https://github.com/NoCheatPlus/NoCheatPlus/#obtain-a-build-of-spigot)
* [Download NoCheatPlus](https://github.com/NoCheatPlus/NoCheatPlus/#download)
* Drop the NoCheatPlus.jar in to the plugins folder.
* Start your Spigot/CraftBukkit server. (Using /reload can have unwanted side effects with players still online, but also with complex plugins and cross-plugin dependencies, so we don't recommend it. Usually it should work with NCP.)

Hints
---------
* Be sure that your Spigot/CraftBukkit and NoCheatPlus versions match together. The latest version of NCP is compatible with a wide range of CraftBukkit/Spigot versions ([See the notable builds page for a quick overview.]()).
* Don't use tabs in the config.yml file.
* Use [ProtocolLib](https://dev.bukkit.org/bukkit-plugins/protocollib) for full efficiency of the fight checks and other. Using a version of ProtocolLib that is supported by NCP is essential, as otherwise some checks will be disabled.
* For compatibility with other plugins such as mcMMO, citizens and more check out [CompatNoCheatPlus](https://dev.bukkit.org/projects/compatnocheatplus-cncp/).
