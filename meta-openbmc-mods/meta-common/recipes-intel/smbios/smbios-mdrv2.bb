SUMMARY = "SMBIOS MDR version 2 service for Intel based platform"
DESCRIPTION = "SMBIOS MDR version 2 service for Intel based platfrom"

SRC_URI = "git://github.com/openbmc/smbios-mdr.git"
SRCREV = "a427dd1de9e765861a52ce0f73021a282cc6ce4c"

S = "${WORKDIR}/git"

PV = "1.0+git${SRCPV}"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

inherit cmake pkgconfig
inherit obmc-phosphor-systemd

SYSTEMD_SERVICE_${PN} += "smbios-mdrv2.service"
SYSTEMD_SERVICE_${PN} += "xyz.openbmc_project.cpuinfo.service"

DEPENDS += " \
    autoconf-archive-native \
    boost \
    systemd \
    sdbusplus \
    phosphor-dbus-interfaces \
    phosphor-logging \
    libpeci \
    i2c-tools \
    nlohmann-json \
    "

EXTRA_OECMAKE="-DYOCTO=1 -DIPMI_BLOB=0"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'smbios-no-dimm', d)}"
PACKAGECONFIG[smbios-no-dimm] = "-DDIMM_DBUS=OFF, -DDIMM_DBUS=ON"
