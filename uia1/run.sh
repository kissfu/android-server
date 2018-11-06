#!/usr/bin/env bash

#根据进程名杀死进程 uiautomator
if [ $# -lt 1 ]
then
  echo "缺少参数：procedure_name"
  exit 1
fi

isDebug=false
if [ $# == 2 ]
then
  isDebug="$2"
fi
PROCESS=`adb shell ps |grep $1|grep -v grep|grep -v PPID|awk '{ print $2}'`
for i in $PROCESS
do
  echo "Kill the $1 process [ $i ]"
  adb shell kill -9 $i
done

target='uia1.jar'
destDir='./dest'

#echo "dex..."
#./ui.sh
echo "delete..."
adb shell rm -f /data/local/tmp/${target}
echo "push..."
adb push ${destDir}/${target} /data/local/tmp/${target}
echo "runtest...$*"
adb shell uiautomator runtest /data/local/tmp/${target} -c com.testerkit.uia1.TestCase1#runTest --nohup  -e debug ${isDebug}
echo 'OK ^_^..........'
