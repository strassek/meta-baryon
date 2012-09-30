IMAGE_FEATURES = "nfs-server package-management ssh-server-dropbear debug-tweaks"

inherit core-image

IMAGE_INSTALL += "samba procps mdadm e2fsprogs-mke2fs util-linux \
                     webmin \
                     webmin-module-status \
                     webmin-module-proc \
                     webmin-module-mount \
                     webmin-module-fdisk \
                     webmin-module-lvm \
                     webmin-module-raid \
                     webmin-module-samba \
                     webmin-module-servers \
                     webmin-module-proftpd \
                     webmin-module-exports \
                     webmin-module-init \
                     webmin-module-net \
                     webmin-module-webmin \
                     webmin-module-useradmin \
                     webmin-module-time \
                     webmin-module-webmincron \
                     webmin-notice \
                     webmin-module-acl \
                     webmin-module-minidlna \
                     minidlna \
                     proftpd"

