PRINC := "${@int(PRINC) + 1}"

# Work around linux 3.4 nfsd regression
do_install_prepend () {
	install -d ${D}/var/lib/nfs/v4recovery
}

FILES_${PN} += "/var/lib/nfs/v4recovery"
