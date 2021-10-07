package org.validator.tck.draft4.optional.format

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class UriTest {
  @Test
  public fun validation_of_URIs(): Unit {
    val schema = "{\"format\":\"uri\"}"
    val `a_valid_URL_with_anchor_tag` = "\"http://foo.bar/?baz=qux#quux\""
    run_test(schema, `a_valid_URL_with_anchor_tag`, true, "DRAFT_4")
    val `a_valid_URL_with_anchor_tag_and_parentheses` =
        "\"http://foo.com/blah_(wikipedia)_blah#cite-1\""
    run_test(schema, `a_valid_URL_with_anchor_tag_and_parentheses`, true, "DRAFT_4")
    val `a_valid_URL_with_URL_encoded_stuff` = "\"http://foo.bar/?q=Test%20URL-encoded%20stuff\""
    run_test(schema, `a_valid_URL_with_URL_encoded_stuff`, true, "DRAFT_4")
    val `a_valid_puny_coded_URL_` = "\"http://xn--nw2a.xn--j6w193g/\""
    run_test(schema, `a_valid_puny_coded_URL_`, true, "DRAFT_4")
    val `a_valid_URL_with_many_special_characters` =
        "\"http://-.~_!${'$'}&'()*+,;=:%40:80%2f::::::@example.com\""
    run_test(schema, `a_valid_URL_with_many_special_characters`, true, "DRAFT_4")
    val `a_valid_URL_based_on_IPv4` = "\"http://223.255.255.254\""
    run_test(schema, `a_valid_URL_based_on_IPv4`, true, "DRAFT_4")
    val `a_valid_URL_with_ftp_scheme` = "\"ftp://ftp.is.co.za/rfc/rfc1808.txt\""
    run_test(schema, `a_valid_URL_with_ftp_scheme`, true, "DRAFT_4")
    val `a_valid_URL_for_a_simple_text_file` = "\"http://www.ietf.org/rfc/rfc2396.txt\""
    run_test(schema, `a_valid_URL_for_a_simple_text_file`, true, "DRAFT_4")
    val `a_valid_URL_` = "\"ldap://[2001:db8::7]/c=GB?objectClass?one\""
    run_test(schema, `a_valid_URL_`, true, "DRAFT_4")
    val `a_valid_mailto_URI` = "\"mailto:John.Doe@example.com\""
    run_test(schema, `a_valid_mailto_URI`, true, "DRAFT_4")
    val `a_valid_newsgroup_URI` = "\"news:comp.infosystems.www.servers.unix\""
    run_test(schema, `a_valid_newsgroup_URI`, true, "DRAFT_4")
    val `a_valid_tel_URI` = "\"tel:+1-816-555-1212\""
    run_test(schema, `a_valid_tel_URI`, true, "DRAFT_4")
    val `a_valid_URN` = "\"urn:oasis:names:specification:docbook:dtd:xml:4.1.2\""
    run_test(schema, `a_valid_URN`, true, "DRAFT_4")
    val `an_invalid_protocol_relative_URI_Reference` = "\"//foo.bar/?baz=qux#quux\""
    run_test(schema, `an_invalid_protocol_relative_URI_Reference`, false, "DRAFT_4")
    val `an_invalid_relative_URI_Reference` = "\"/abc\""
    run_test(schema, `an_invalid_relative_URI_Reference`, false, "DRAFT_4")
    val `an_invalid_URI` = "\"\\\\\\\\WINDOWS\\\\fileshare\""
    run_test(schema, `an_invalid_URI`, false, "DRAFT_4")
    val `an_invalid_URI_though_valid_URI_reference` = "\"abc\""
    run_test(schema, `an_invalid_URI_though_valid_URI_reference`, false, "DRAFT_4")
    val `an_invalid_URI_with_spaces` = "\"http:// shouldfail.com\""
    run_test(schema, `an_invalid_URI_with_spaces`, false, "DRAFT_4")
    val `an_invalid_URI_with_spaces_and_missing_scheme` = "\":// should fail\""
    run_test(schema, `an_invalid_URI_with_spaces_and_missing_scheme`, false, "DRAFT_4")
    val `an_invalid_URI_with_comma_in_scheme` = "\"bar,baz:foo\""
    run_test(schema, `an_invalid_URI_with_comma_in_scheme`, false, "DRAFT_4")
  }
}
