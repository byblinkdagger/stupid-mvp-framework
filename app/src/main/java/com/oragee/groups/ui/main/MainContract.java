package com.oragee.groups.ui.main;

import com.oragee.groups.base.IM;
import com.oragee.groups.base.IP;
import com.oragee.groups.base.IV;

/**
 * Created by lucky on 2017/11/24.
 * Here be dragons
 * 前方高能
 */

public class MainContract {

    interface V extends IV {
        //set ui
    }

    interface P extends IP {
    }

    interface M extends IM {
        //call net request to get observable
    }

}
