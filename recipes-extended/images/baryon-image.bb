IMAGE_FEATURES = "nfs-server package-management ssh-server-dropbear debug-tweaks"

POKY_EXTRA_INSTALL = "samba procps mdadm e2fsprogs-mke2fs util-linux \
                     webmin \
                     webmin-module-status \
                     webmin-module-proc \
                     webmin-module-mount \
                     webmin-module-fdisk \
                     webmin-module-raid \
                     webmin-module-samba \
                     webmin-module-proftpd \
                     webmin-module-exports \
                     webmin-module-init \
                     webmin-module-net \
                     webmin-module-webmin"

inherit core-image
