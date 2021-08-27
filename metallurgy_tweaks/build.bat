:: Builds a zip with the core metallurgy_tweaks scripts and copies it into the mod assets
:: !! Requires 7-Zip to be installed and added to the environment variables !!
cd scripts
7z a ..\metallurgy_tweaks.zip .\*
cd ..
copy metallurgy_tweaks.zip ..\src\main\resources\assets\metallurgy\tweaks\script\metallurgy_tweaks.zip
copy metallurgy_tweaks_config.zs ..\src\main\resources\assets\metallurgy\tweaks\script\metallurgy_tweaks_config.zs
PAUSE

