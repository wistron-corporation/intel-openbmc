EXTRA_OECMAKE += "${@bb.utils.contains('IMAGE_FSTYPES', 'intel-pfr', '-DINTEL_PFR_ENABLED=ON', '', d)}"
EXTRA_OECMAKE += "${@bb.utils.contains('EXTRA_IMAGE_FEATURES', 'validation-unsecure', '-DBMC_VALIDATION_UNSECURE_FEATURE=ON', '', d)}"
EXTRA_OECMAKE += "-DUSING_ENTITY_MANAGER_DECORATORS=OFF"
SRC_URI = "git://github.com/openbmc/intel-ipmi-oem.git"
SRCREV = "a87dbd4ee3ff53e7e53052c1663f85b390a0ab31"

FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

