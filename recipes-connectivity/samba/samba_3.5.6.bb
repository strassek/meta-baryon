require samba.inc
require samba-basic.inc
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://../COPYING;md5=d32239bcb673463ab874e80d47fae504"

PR = "r1"

SRC_URI += "file://config-h.patch \
            file://tdbheaderfix.patch;patchdir=.."

SRC_URI[md5sum] = "bf6c09ea497a166df8bd672db1d8da8f"
SRC_URI[sha256sum] = "466410868375d19a286ac3fc5d9f3c267ce359189f8e0d76e72ec10bd54247da"

S = "${WORKDIR}/samba-${PV}/source3"

EXTRA_OECONF += "\
	ac_cv_path_PYTHON=/not/exist \
	ac_cv_path_PYTHON_CONFIG=/not/exist \
	SMB_BUILD_CC_NEGATIVE_ENUM_VALUES=yes \
	samba_cv_CC_NEGATIVE_ENUM_VALUES=yes \
	linux_getgrouplist_ok=no \
	samba_cv_HAVE_BROKEN_GETGROUPS=no \
	samba_cv_HAVE_FTRUNCATE_EXTEND=yes \
	samba_cv_have_setresuid=yes \
	samba_cv_have_setresgid=yes \
	samba_cv_HAVE_WRFILE_KEYTAB=yes \
	samba_cv_linux_getgrouplist_ok=yes \
	"

do_configure() {
	oe_runconf
}

do_compile () {
	base_do_compile
}

pkg_postinst_${PN} () {
    if [ "x$D" != "x" ] ; then
        exit 1
    fi

    # create the samba user, and add user to group users
    username='smbuser'
    adduser --disabled-password $username
    # FIXME: use addgroup if busybox addgroup is ready
    sed -i -e "s/^users:.*/&${username}/g" /etc/group
    mkdir /home/${username}/smbdata
    chown -R smbuser:users /home/${username}/smbdata
}
