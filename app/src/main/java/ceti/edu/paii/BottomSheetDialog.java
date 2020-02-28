package ceti.edu.paii;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    private BottomSheetListener bottomSheetListener;

    private String ANSWER_1 = "YES";
    private String ANSWER_2 = "NO";
    private String MESSAGE_ERROR = "must Implement BOTTOM SHEET";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,container,false);

        Button btnYes = v.findViewById(R.id.sheet_botton1);
        Button btnNo  = v.findViewById(R.id.sheet_botton2);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetListener.onBottomClicked(ANSWER_1);
                dismiss();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetListener.onBottomClicked(ANSWER_2);
                dismiss();
            }
        });

        return v;
    }

    public interface BottomSheetListener{
        void onBottomClicked(String text);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            bottomSheetListener = (BottomSheetListener) context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+ MESSAGE_ERROR);
        }
    }
}
