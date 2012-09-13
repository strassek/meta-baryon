DESCRIPTION = "Secure ftp daemon"
SECTION = "console/network"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb0d1484d11915fa88a6a7702f1dc184"

PR = "r0"

SRC_URI = "ftp://ftp.proftpd.org/distrib/source/${PN}-${PV}.tar.gz \
	file://make.patch \
	file://basic.conf.patch \
	file://contrib.patch \
	"

SRC_URI[md5sum] = "0871e0b93c9c3c88ca950b6d9a04aed2"
SRC_URI[sha256sum] = "9f659585cea90fc6af34a0ffae4a90e4ed37abe92dbd9b6c311f95a436c961cb"

EXTRA_OECONF = "ac_cv_func_setpgrp_void=yes ac_cv_func_setgrent_void=yes --disable-cap"
LDFLAGS += "-Llib"
PARALLEL_MAKE = ""

do_configure () {
	 ./configure \
		   --disable-auth-pam \
                   --build=${BUILD_SYS} \
                   --host=${HOST_SYS} \
                   --target=${TARGET_SYS} \
                   --prefix=/usr \
		   --sysconfdir=/etc \
		   --sharedstatedir=/com \
		   --localstatedir=/var \
                   ${EXTRA_OECONF} \
                   $@;
}

do_install () {
    oe_runmake DESTDIR=${D} install
}

pkg_postinst () {
    if [ "x$D" != "x" ] ; then
        exit 1
    fi

    # more chown's might be needed
    chown root:root /usr/sbin/proftpd

    # create the ftp user
    username='ftp'
    addgroup ${username}
    adduser --disabled-password ${username} --ingroup ${username}
    mkdir -p /home/${username}/pub/
    chown -R ftp:ftp /home/${username}/pub
}
