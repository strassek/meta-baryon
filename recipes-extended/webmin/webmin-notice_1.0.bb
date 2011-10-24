DESCRIPTION = "Web administration notice script"
LICENSE = "MIT"
SRC_URI = "file://webmin-notice.sh"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
RDEPENDS_${PN} = "webmin"

inherit allarch update-rc.d

INITSCRIPT_NAME = "webmin-notice"
INITSCRIPT_PARAMS = "start 99 5 3 ."

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 webmin-notice.sh ${D}${sysconfdir}/init.d/webmin-notice
}

# No sundry packages
PACKAGES = "${PN}"
