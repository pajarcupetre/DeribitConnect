package models

import org.junit.Test
import play.api.libs.json.Json


class ModelsTest {

  @Test
  def parserTest(): Unit = {
    val input = "{\"token_type\":\"bearer\",\"scope\":\"account:read_write block_trade:read_write custody:read_write mainaccount name:scala session:rest-PRwURohyuaQ= trade:read_write wallet:read_write\",\"refresh_token\":\"1638173662004.1CnHBAeW.cKrdyl5pH7KTO2DJBInzCv58lYVRpH2f3FyLmmc9LFC-UxRN_Cr2fcezOgjDLvDYoKA2Ig4leS2dFe7CbpPTAjqGW6stxfZUYbUN2WDfDJVFYG8v38FsG7J_82nRk3zmKV2VA5Zp2tOlmJliOx1JP7YKcraSSuXR_25I43J4prRhFRJgP00k5qZheUKy3orhRreCkhsSpyAoL-gtmMp9fKLP2CEQPpnfgUfVYa_649JQwILrmQXTFq172KxBl666rR_Usydzrbb16LdF0Uq-Xw7baLwFP7TXgzjw7S5NgAX5UhZhBsnVD0vgqLaSp1FSMDuKiBMndZqoG9-oH8P318-9eNgUug\",\"expires_in\":899,\"access_token\":\"1637569762004.1J0ISf9Y.RvjryJmHKHOlwywvOMETdaYSZvtg5IIUvEVk5u7-Wd0Momir1Ki9kUaoBxxxJld9tn7DDKoQfZ97Nhhotbm4Dri-xdhwRgnHg47aQor74VAqjZMA54a2OvOlP_zf2dprglm_Ana4ZAGEzPXOzzgfDvfYrFBUAzK5WKG5IRRQNkhwcjQCpyHGyPwArvn2E6Vq3RJliooLfHCK3cFlUCJdtFi_5QUAUrkvknCXz7YCibV_u6hjvsHEP5RtDZ1_0MLB4_lsBsptFip2Slhp6wxiW-tsXcuzUELCZuPeNia-7O0cGd4r7Z1bikCObSCzUbRlYHLT2wQTg94ZQnBv9Xlvgv2E0IZV2ww\"}"

    val response = Json.parse(input).as[AuthenticationDetails]
    assert(response.expires_in.equals(899))

    val fullInput = "{\"jsonrpc\":\"2.0\",\"result\":{\"token_type\":\"bearer\",\"scope\":\"account:read_write block_trade:read_write custody:read_write mainaccount name:scala session:rest-PRwURohyuaQ= trade:read_write wallet:read_write\",\"refresh_token\":\"1638173662004.1CnHBAeW.cKrdyl5pH7KTO2DJBInzCv58lYVRpH2f3FyLmmc9LFC-UxRN_Cr2fcezOgjDLvDYoKA2Ig4leS2dFe7CbpPTAjqGW6stxfZUYbUN2WDfDJVFYG8v38FsG7J_82nRk3zmKV2VA5Zp2tOlmJliOx1JP7YKcraSSuXR_25I43J4prRhFRJgP00k5qZheUKy3orhRreCkhsSpyAoL-gtmMp9fKLP2CEQPpnfgUfVYa_649JQwILrmQXTFq172KxBl666rR_Usydzrbb16LdF0Uq-Xw7baLwFP7TXgzjw7S5NgAX5UhZhBsnVD0vgqLaSp1FSMDuKiBMndZqoG9-oH8P318-9eNgUug\",\"expires_in\":899,\"access_token\":\"1637569762004.1J0ISf9Y.RvjryJmHKHOlwywvOMETdaYSZvtg5IIUvEVk5u7-Wd0Momir1Ki9kUaoBxxxJld9tn7DDKoQfZ97Nhhotbm4Dri-xdhwRgnHg47aQor74VAqjZMA54a2OvOlP_zf2dprglm_Ana4ZAGEzPXOzzgfDvfYrFBUAzK5WKG5IRRQNkhwcjQCpyHGyPwArvn2E6Vq3RJliooLfHCK3cFlUCJdtFi_5QUAUrkvknCXz7YCibV_u6hjvsHEP5RtDZ1_0MLB4_lsBsptFip2Slhp6wxiW-tsXcuzUELCZuPeNia-7O0cGd4r7Z1bikCObSCzUbRlYHLT2wQTg94ZQnBv9Xlvgv2E0IZV2ww\"},\"usIn\":1637568862004661,\"usOut\":1637568862005180,\"usDiff\":519,\"testnet\":true}"

    val fullResponse = Json.parse(fullInput).as[AuthenticationResponse]
    assert(fullResponse.testnet)
  }

}
