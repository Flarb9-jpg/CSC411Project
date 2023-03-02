#!system/bin/sh
while :
do
sync
echo 3> /proc/sys/vm/drop_caches
echo "Script is been launched"
sleep 30m
done
exit 0;