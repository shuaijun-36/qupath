import qupath.lib.common.GeneralTools
import qupath.lib.common.Timeit
import qupath.lib.images.writers.ome.OMEPyramidWriter

import java.util.stream.IntStream

import static qupath.lib.scripting.QP.*

def server = getCurrentServer()
def name = GeneralTools.getNameWithoutExtension(getCurrentImageName())
def timeit = new Timeit()
println server.nChannels()
for (int channel = 0; channel < server.nChannels(); channel++) {
    def writer = new OMEPyramidWriter.Builder(server)
        .channels(channel)
        .build()
    def dir = buildFilePath(PROJECT_BASE_DIR, "export")
    mkdirs(dir)
    def path = buildFilePath(dir, "$name (c=$channel).ome.tif")
    writer.writeSeries(path)
}
println timeit.stop()