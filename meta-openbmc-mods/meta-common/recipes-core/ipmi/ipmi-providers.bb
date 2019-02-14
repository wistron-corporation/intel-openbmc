SUMMARY = "Intel IPMI Providers"
DESCRIPTION = "IPMI Provider Libraries"

SRC_URI = "git://git@github.com/Intel-BMC/intel-ipmi-providers;protocol=ssh"
SRCREV = "3573b25576d14b3334f93bd988c6e2003fab8f90"

S = "${WORKDIR}/git"
PV = "0.1+git${SRCPV}"

DEPENDS = "boost phosphor-ipmi-host intel-ipmi-oem systemd microsoft-gsl"

inherit cmake obmc-phosphor-ipmiprovider-symlink

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

EXTRA_OECMAKE="-DENABLE_TEST=0 -DYOCTO=1"

LIBRARY_NAMES += "libmtmcmds.so"
LIBRARY_NAMES += "libsmbioshandler.so"
LIBRARY_NAMES += "libzbridgecmd.so"
LIBRARY_NAMES += "libsmbiosmdrv2.so"
LIBRARY_NAMES += "libfwupdcmds.so"

HOSTIPMI_PROVIDER_LIBRARY += "${LIBRARY_NAMES}"
NETIPMI_PROVIDER_LIBRARY += "${LIBRARY_NAMES}"

FILES_${PN}_append = " ${libdir}/ipmid-providers/lib*${SOLIBS}"
FILES_${PN}_append = " ${libdir}/host-ipmid/lib*${SOLIBS}"
FILES_${PN}_append = " ${libdir}/net-ipmid/lib*${SOLIBS}"
FILES_${PN}-dev_append = " ${libdir}/ipmid-providers/lib*${SOLIBSDEV}"

do_configure_prepend() {
    cp -r ${WORKDIR}/recipe-sysroot${libdir}/phosphor-ipmi-host ${S}
    cp -r ${WORKDIR}/recipe-sysroot${includedir}/phosphor-ipmi-host ${S}
    cp -r ${WORKDIR}/recipe-sysroot${includedir}/intel-ipmi-oem ${S}
}
