/* By obtaining and/or copying this work, you agree that you have read,
 * understood and will comply with the following terms and conditions.
 *
 * Copyright (c) 2020 Mesibo
 * https://mesibo.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the terms and condition mentioned
 * on https://mesibo.com as well as following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions, the following disclaimer and links to documentation and
 * source code repository.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of Mesibo nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior
 * written permission.
 *
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * Documentation
 * https://mesibo.com/documentation/
 *
 * Source Code Repository
 * https://github.com/mesibo/ui-modules-android

 */
package com.mesibo.uihelper;

/**
 * Created by root on 11/3/16.
 */

public class CountyCodeUtils {

    public static final CountryInfo[] countryInfo = {new CountryInfo("Afghanistan", 93), new CountryInfo("Albania", 355), new CountryInfo("Algeria", 213), new CountryInfo("American Samoa", 1684), new CountryInfo("Andorra", 376), new CountryInfo("Angola", 244), new CountryInfo("Anguilla", 1264), new CountryInfo("Antigua", 1268), new CountryInfo("Argentina", 54), new CountryInfo("Armenia", 374), new CountryInfo("Aruba", 297), new CountryInfo("Australia", 61), new CountryInfo("Austria", 43), new CountryInfo("Azerbaijan", 994), new CountryInfo("Bahamas", 1242), new CountryInfo("Bahrain", 973), new CountryInfo("Bangladesh", 880), new CountryInfo("Barbados", 1246), new CountryInfo("Belarus", 375), new CountryInfo("Belgium", 32), new CountryInfo("Belize", 501), new CountryInfo("Benin", 229), new CountryInfo("Bermuda", 1441), new CountryInfo("Bhutan", 975), new CountryInfo("Bolivia", 591), new CountryInfo("Bosnia", 387), new CountryInfo("Botswana", 267), new CountryInfo("Brazil", 55), new CountryInfo("British Virgin Islands", 1284), new CountryInfo("Brunei", 673), new CountryInfo("Bulgaria", 359), new CountryInfo("Burkina Faso", 226), new CountryInfo("Burundi", 257), new CountryInfo("Cambodia", 855), new CountryInfo("Cameroon", 237), new CountryInfo("Canada", 1), new CountryInfo("Cape Verde", 238), new CountryInfo("Cayman Islands", 1345), new CountryInfo("Central African Republic", 236), new CountryInfo("Chad", 235), new CountryInfo("Chile", 56), new CountryInfo("China", 86), new CountryInfo("Colombia", 57), new CountryInfo("Comoros", 269), new CountryInfo("Congo Brazzaville", 242), new CountryInfo("Cook", 682), new CountryInfo("Costa Rica", 506), new CountryInfo("Croatia", 385), new CountryInfo("Cuba", 53), new CountryInfo("Cyprus", 357), new CountryInfo("Czech Republic", 420), new CountryInfo("Denmark", 45), new CountryInfo("Djibouti", 253), new CountryInfo("Dominica", 1767), new CountryInfo("Dominican Republic", 1809), new CountryInfo("Dominican Republic", 1829), new CountryInfo("Dominican Republic", 1849), new CountryInfo("DR of Congo", 243), new CountryInfo("East Timor", 670), new CountryInfo("Ecuador", 593), new CountryInfo("Egypt", 20), new CountryInfo("El Salvador", 503), new CountryInfo("Equatorial Guinea", 240), new CountryInfo("Eritrea", 291), new CountryInfo("Estonia", 372), new CountryInfo("Ethiopia", 251), new CountryInfo("Faeroe", 298), new CountryInfo("Falklands", 500), new CountryInfo("Fiji", 679), new CountryInfo("Finland", 358), new CountryInfo("France", 33), new CountryInfo("French Guiana", 594), new CountryInfo("French Polynesia", 689), new CountryInfo("Gabon", 241), new CountryInfo("Gambia", 220), new CountryInfo("Georgia", 995), new CountryInfo("Germany", 49), new CountryInfo("Ghana", 233), new CountryInfo("Gibraltar", 350), new CountryInfo("Greece", 30), new CountryInfo("Greenland", 299), new CountryInfo("Grenada", 1473), new CountryInfo("Guadeloupe", 590), new CountryInfo("Guam", 1671), new CountryInfo("Guatemala", 502), new CountryInfo("Guinea Bissau", 245), new CountryInfo("Guinea Republic", 224), new CountryInfo("Guyana", 592), new CountryInfo("Haiti", 509), new CountryInfo("Honduras", 504), new CountryInfo("Hong Kong", 852), new CountryInfo("Hungary", 36), new CountryInfo("Iceland", 354), new CountryInfo("India", 91), new CountryInfo("Indonesia", 62), new CountryInfo("Iran", 98), new CountryInfo("Iraq", 964), new CountryInfo("Ireland", 353), new CountryInfo("Israel", 972), new CountryInfo("Italy", 39), new CountryInfo("Ivory Coast", 225), new CountryInfo("Jamaica", 1876), new CountryInfo("Japan", 81), new CountryInfo("Jordan", 962), new CountryInfo("Kazakhstan", 76), new CountryInfo("Kazakhstan", 77), new CountryInfo("Kenya", 254), new CountryInfo("Kiribati", 686), new CountryInfo("Kuwait", 965), new CountryInfo("Kyrghyzstan", 996), new CountryInfo("Laos", 856), new CountryInfo("Latvia", 371), new CountryInfo("Lebanon", 961), new CountryInfo("Lesotho", 266), new CountryInfo("Liberia", 231), new CountryInfo("Libya", 218), new CountryInfo("Liechtenstein", 423), new CountryInfo("Lithuania", 370), new CountryInfo("Luxemburg", 352), new CountryInfo("Macau", 853), new CountryInfo("Macedonia", 389), new CountryInfo("Madagascar", 261), new CountryInfo("Malawi", 265), new CountryInfo("Malaysia", 60), new CountryInfo("Maldives", 960), new CountryInfo("Mali", 223), new CountryInfo("Malta", 356), new CountryInfo("Marshall Islands", 692), new CountryInfo("Martinique", 596), new CountryInfo("Mauritania", 222), new CountryInfo("Mauritius", 230), new CountryInfo("Mexico", 52), new CountryInfo("Micronesia", 691), new CountryInfo("Moldova", 373), new CountryInfo("Monaco", 377), new CountryInfo("Mongolia", 976), new CountryInfo("Montenegro", 382), new CountryInfo("Montserrat", 1664), new CountryInfo("Morocco", 212), new CountryInfo("Mozambique", 258), new CountryInfo("Myanmar", 95), new CountryInfo("Namibia", 264), new CountryInfo("Nauru", 674), new CountryInfo("Nepal", 977), new CountryInfo("Netherlands", 31), new CountryInfo("Netherlands Antillas", 599), new CountryInfo("New Caledonia", 687), new CountryInfo("New Zealand", 64), new CountryInfo("Nicaragua", 505), new CountryInfo("Niger", 227), new CountryInfo("Nigeria", 234), new CountryInfo("North Korea", 850), new CountryInfo("Norway", 47), new CountryInfo("Oman", 968), new CountryInfo("Pakistan", 92), new CountryInfo("Palau", 680), new CountryInfo("Palestine Authority", 970), new CountryInfo("Panama", 507), new CountryInfo("Papua New Guinea", 675), new CountryInfo("Paraguay", 595), new CountryInfo("Peru", 51), new CountryInfo("Philippines", 63), new CountryInfo("Poland", 48), new CountryInfo("Portugal", 351), new CountryInfo("Puerto Rico", 1787), new CountryInfo("Puerto Rico", 1939), new CountryInfo("Qatar", 974), new CountryInfo("Reunion", 262), new CountryInfo("Romania", 40), new CountryInfo("Russia", 7), new CountryInfo("Rwanda", 250), new CountryInfo("Saint Kitts and Nevis", 1869), new CountryInfo("Saint Lucia", 1758), new CountryInfo("Saint Vincent and the Grenadines", 1784), new CountryInfo("San Marino", 378), new CountryInfo("Sao Tome & Principe", 239), new CountryInfo("Saudi Arabia", 966), new CountryInfo("Senegal", 221), new CountryInfo("Serbia", 381), new CountryInfo("Seychelles", 248), new CountryInfo("Sierra Leone", 232), new CountryInfo("Singapore", 65), new CountryInfo("Slovakia", 421), new CountryInfo("Slovenia", 386), new CountryInfo("Solomon Islands", 677), new CountryInfo("Somalia", 252), new CountryInfo("South Africa", 27), new CountryInfo("South Korea", 82), new CountryInfo("Spain", 34), new CountryInfo("Sri Lanka", 94), new CountryInfo("Sudan", 249), new CountryInfo("Suriname", 597), new CountryInfo("Swaziland", 268), new CountryInfo("Sweden", 46), new CountryInfo("Switzerland", 41), new CountryInfo("Syria", 963), new CountryInfo("Taiwan", 886), new CountryInfo("Tajikistan", 992), new CountryInfo("Tanzania", 255), new CountryInfo("Thailand", 66), new CountryInfo("Togo", 228), new CountryInfo("Tonga", 676), new CountryInfo("Trinidad & Tobago", 1868), new CountryInfo("Tunisia", 216), new CountryInfo("Turkey", 90), new CountryInfo("Turkmenistan", 993), new CountryInfo("Turks and Caicos", 1649), new CountryInfo("Uganda", 256), new CountryInfo("Ukraine", 380), new CountryInfo("United Arab Emirates", 971), new CountryInfo("United Kingdom", 44), new CountryInfo("United States", 1), new CountryInfo("Uruguay", 598), new CountryInfo("US Virgin Islands", 1340), new CountryInfo("Uzbekistan", 998), new CountryInfo("Vanuatu", 678), new CountryInfo("Venezuela", 58), new CountryInfo("Vietnam", 84), new CountryInfo("Wallis and Futuna", 681), new CountryInfo("Western Samoa", 685), new CountryInfo("Yemen", 967), new CountryInfo("Zambia", 260), new CountryInfo("Zimbabwe", 263)};

    public static class CountryInfo {
        public String countryName = "", shortName="";
        public int countryCode = 0;

        public CountryInfo(String cn, int cc) {
            countryName = cn;
            shortName = null;
            countryCode = cc;
        }

        public CountryInfo(String cn, String sn, int cc) {
            countryName = cn;
            shortName = sn;
            countryCode = cc;
        }
    }

}
