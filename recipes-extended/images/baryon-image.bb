IMAGE_FEATURES = "nfs-server package-management debug-tweaks"

POKY_EXTRA_INSTALL = "samba lvm2 procps mdadm e2fsprogs-mke2fs util-linux \
                     webmin \
                     webmin-module-status \
                     webmin-module-proc \
                     webmin-module-mount \
                     webmin-module-fdisk \
                     webmin-module-lvm \
                     webmin-module-raid \
                     webmin-module-samba \
                     webmin-module-proftpd \
                     webmin-module-exports \
                     webmin-module-init \
                     webmin-module-net \
                     webmin-module-webmin"

inherit core-image
