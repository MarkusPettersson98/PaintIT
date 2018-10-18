import os
import subprocess

test = subprocess.Popen(["ping","-W","2","-c", "1", "192.168.1.70"], stdout=subprocess.PIPE)
output = test.communicate()[0]

PaintITLogo ="""

 _______            __              __     ______  ________ 
/       \          /  |            /  |   /      |/        |
$$$$$$$  | ______  $$/  _______   _$$ |_  $$$$$$/ $$$$$$$$/ 
$$ |__$$ |/      \ /  |/       \ / $$   |   $$ |     $$ |   
$$    $$/ $$$$$$  |$$ |$$$$$$$  |$$$$$$/    $$ |     $$ |   
$$$$$$$/  /    $$ |$$ |$$ |  $$ |  $$ | __  $$ |     $$ |   
$$ |     /$$$$$$$ |$$ |$$ |  $$ |  $$ |/  |_$$ |_    $$ |   
$$ |     $$    $$ |$$ |$$ |  $$ |  $$  $$// $$   |   $$ |   
$$/       $$$$$$$/ $$/ $$/   $$/    $$$$/ $$$$$$/    $$/    
                                                            
                                                            
                                                            

""" ;
print(PaintITLogo)
os.system("mvn clean && mvn package -Dmaven.test.skip=true")
