<?php
/*
 * @description A library of Facebook PHP utilities.
 * @package FezBook
 * @version 0.1.1
 * @author Adrian J. Cruz
 * @license http://www.opensource.org/licenses/bsd-license.php <2012>, <Adrian J. Cruz> 
 */
class FezBook {
    public $graph = 'https://graph.facebook.com/';

    function __construct() {
        if (!function_exists('json_decode')) {
            printf("This library requires json\n");
            exit;
        }
        if (!function_exists('curl_init')) {
            printf("This library requires curl\n");
            exit;
        }
        $this->me = $this->graph . 'me';
    }

    /*
     * @description HTTP request http://php.net/manual/en/book.curl.php
     * @param string $uri uri to grab
     * @param string $method POST|default=GET
     * @param string $postdata post data in urlencoded format: $postdata = "access_token=".$token."&message=".$status
     * @return page results
     */
    function curl($uri, $method=null, $postdata=null) {
        if (!$uri)
            return false;
        $c = curl_init();
        if ('POST' === $method) {
            curl_setopt($c, CURLOPT_POST, TRUE);
            if ($postdata) {
                curl_setopt($c, CURLOPT_POSTFIELDS, $postdata);
            }
        }
        curl_setopt($c, CURLOPT_URL, $uri);
        curl_setopt($c, CURLOPT_USERAGENT, 'FezBook PHP Library');
	    curl_setopt($c, CURLOPT_HEADER, false);
        curl_setopt($c, CURLOPT_CONNECTTIMEOUT, 60);
        curl_setopt($c, CURLOPT_TIMEOUT, 60);
	    curl_setopt($c, CURLOPT_REFERER, $uri);
        curl_setopt($c, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($c, CURLOPT_SSL_VERIFYPEER, false);
        $ret = curl_exec($c);
        curl_close($c);
        return $ret;
    }

    /*
     * @description grab accounts associated with the fb account
     * @param string $token oAuth token
     * @param int $fbid fb id
     * @return object 
     */
    function get_accounts($token,$fbid=null) {
        if (!$token)
            return false;
        $uri = ($fbid) ? $this->graph . $fbid : $this->me;
        $accounts = $this->curl($uri . '/accounts?access_token=' . $token);
        if ($accounts) {
            $accounts = json_decode($accounts);
            if ($accounts->data) {
                return $accounts;
            }
            elseif ($accounts->error) {
                return $accounts->error;
            }
        }
        else {
            return false;
        }
    }

    /*
     * @description get facebook data using $token
     * @param string $token facebook token
     * @param int $fbid facebook id
     * @return array $data json_decode'd data
     */
    function get_fbdata($token,$fbid=null) {
        if (!$token)
            return false;
        $uri = ($fbid) ? $this->graph . $fbid : $this->me;
        $data = $this->curl($uri . '?access_token=' . $token);
        if ($data) {
            $data = json_decode($data);
            return $data;
        }
        else {
            return false;
        }
    }

    /*
     * @description get facebook feed
     * @param string $token facebook access_token
     * @param int $fbid facebook id
     * @return array $data json_decode'd data
     */
    function get_feed($token,$fbid=null) {
        if (!$token)
            return false;
        $uri = ($fbid) ? $this->graph . $fbid : $this->me;
        $data = $this->curl($uri . '/feed?access_token=' . $token);
        if ($data) {
            $data = json_decode($data);
            return $data;
        }
        else {
            return false;
        }
    }

    /*
     * @description post to facebook feed. for more info: http://developers.facebook.com/docs/reference/api/post/
     * @param string $token facebook access_token
     * @param string $postdata POST data in urlencoded format: $postdata = "access_token=".$token."&message=".$status
     * @param int $fbid facebook id
     */
    function post_to_feed($token,$postdata,$fbid=null) {
        if (!$token || !$postdata)
            return false;
        $uri = ($fbid) ? $this->graph . $fbid : $this->me;
        $data = $this->curl($uri . '/feed&access_token='.$token, 'POST', $postdata);
        if ($data) {
            $data = json_decode($data);
            return $data;
        }
        else {
            return false;
        }
    }
}
?>
