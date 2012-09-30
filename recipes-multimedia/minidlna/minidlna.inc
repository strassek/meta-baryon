DESCRIPTION = "lightweight DLNA/UPnP-AV server targeted at embedded systems."
HOMEPAGE = "http://minidlna.sourceforge.net/"
LICENSE = "GPLv2 BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENCE;md5=b1a795ac1a06805cf8fd74920bc46b5c \
                    file://LICENCE.miniupnpd;md5=b0dabf9d8e0f871554e309d62ead8d2b"
SECTION = "net"
DEPENDS = "libexif ffmpeg"

INC_PR = "r0"

SRC_URI = "http://downloads.sourceforge.net/project/${PN}/${PN}/${PV}/${PN}_${PV}_src.tar.gz \
           file://init"

CONFFILES_${PN} = "${sysconfdir}/minidlna.conf"

INITSCRIPT_NAME = "minidlna"
INITSCRIPT_PARAMS = "defaults 90 10"

inherit update-rc.d

CFLAGS_prepend = "-D_GNU_SOURCE -D_FILE_OFFSET_BITS=64 -I${STAGING_INCDIR}/ffmpeg "

do_configure() {
    sed -i "s:/usr/include:${STAGING_INCDIR}:g" genconfig.sh
    sed -i "s/^CFLAGS =/#CFLAGS =/" Makefile
    sed -i "s/^CC =/#CC =/" Makefile
    sed -i "/^minidlna\:/ s/[$][(]LIBS[)]//" Makefile
    sed -i "s/--mode=0644//" Makefile
    sed -i "s:^media_dir=/opt:media_dir=/media:" minidlna.conf
    ./genconfig.sh
}

do_install() {
    oe_runmake 'DESTDIR=${D}' install install-conf
    install -d ${D}${sysconfdir}/init.d/
    install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}
}

