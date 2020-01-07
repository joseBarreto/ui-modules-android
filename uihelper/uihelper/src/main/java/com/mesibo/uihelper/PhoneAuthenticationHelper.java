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

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

public class PhoneAuthenticationHelper {

    public interface Listener {
        boolean Mesibo_onPhoneAuthenticationNumber(PhoneNumber phone);
        void Mesibo_onPhoneAuthenticationComplete();
    }

    PhoneAuthentication mAuth;
    public PhoneAuthenticationHelper(AppCompatActivity activity, PhoneAuthenticationHelper.PhoneNumber phone, PhoneAuthenticationHelper.Listener listner, int reqCode) {
        mAuth = new PhoneAuthentication(activity, phone, listner, reqCode);
    }

    public static class PhoneNumber {
        public String mCountryCode = null;
        public String mNationalNumber = null;
        public String mCountryName = null;
        public String mSmartLockUrl = null;
        boolean mValid = false;

        public boolean isValid() {
            return mValid;
        }

        public String getNumber() {


            if(TextUtils.isEmpty(mCountryCode))
                return "";

            String phoneNumber = mCountryCode;

            if(!TextUtils.isEmpty(mNationalNumber))
                phoneNumber += mNationalNumber;

            return phoneNumber;
        }
    }

    public void stop(boolean success) {
        mAuth.stop(success);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAuth.onActivityResult(requestCode, resultCode, data);
    }

    public PhoneNumber update(PhoneNumber phoneNumber) {
        return mAuth.update(phoneNumber);
    }

    public void selectCountry() {
        mAuth.selectCountry();
    }

}
