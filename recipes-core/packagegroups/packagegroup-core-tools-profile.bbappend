# Only include packages that are useful on a headless device

PROFILETOOLS = " \
    oprofile \
    oprofileui-server \
    powertop \
    lttng-control \
    "

RRECOMMENDS_${PN} = " \
    perf \
    trace-cmd \
    kernel-module-oprofile \
    blktrace \
    "
