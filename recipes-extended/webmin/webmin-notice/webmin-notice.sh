#!/bin/sh
# Webmin connect info report script
# Copyright (C) 2011 Intel Corporation
# Distributed under the MIT license

if ! [ -f /var/webmin/miniserv.pid ] ; then
	echo "Webmin not started or failed to start"
	exit
elif ! [ -d /proc/`cat /var/webmin/miniserv.pid` ] ; then
        echo "Webmin not started or failed to start"
        exit
fi

if [ "`pidof avahi-daemon`" != "" ] ; then
	host=`grep "^host-name=" /etc/avahi/avahi-daemon.conf | sed 's/host-name=//'`
	if [ "$host" = "" ] ; then
	        host=`hostname`
	fi
	domain=`grep "^domain-name=" /etc/avahi/avahi-daemon.conf | sed 's/domain-name=//'`
	if [ "$domain" = "" ] ; then
		domain="local"
	fi
	avahiaddr="$host.$domain"
fi

port=`grep "^port=" /etc/webmin/miniserv.conf | sed 's/port=//'`
ssl=`grep "^ssl=" /etc/webmin/miniserv.conf | sed 's/ssl=//'`
if [ "$ssl" = "1" ] ; then
	proto="https"
else
	proto="http"
fi
ipaddr=`ifconfig | grep 'inet addr:'| grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $1}'`

addr="$proto://$ipaddr:$port"
if [ "$avahiaddr" != "" ] ; then
	addr="$proto://$avahiaddr:$port  or  $addr"
fi
echo
echo "Web administration interface is now available at:"
echo "  $addr"

