#!/usr/bin/env bash

dexFile=$1
jarFile=$2
libArr=''
target='uia1.jar'
destDir='./dest'

INDEX=1
#from 3nd to last param 从第二个到最后一个参数
for arg in ${@:3}
do
   #echo "arg = $arg"
   libArr="$libArr $arg"
done
echo '==========>>>>>>>>>>'
echo 'delete...'

rm -rf $destDir

echo 'create...'

if [ ! -d $destDir  ];then
  mkdir $destDir
fi

echo 'dx...'

${dexFile} --dex --output=${destDir}/classes.dex ${destDir} ${libArr}

echo 'jar...'

${jarFile} -cvf ${destDir}/${target} -C ./ ${destDir}/classes.dex

echo 'OK ^_^..........'
