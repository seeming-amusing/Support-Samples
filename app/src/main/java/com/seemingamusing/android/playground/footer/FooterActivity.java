package com.seemingamusing.android.playground.footer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.seemingamusing.android.playground.R;
import com.seemingamusing.android.playground.common.BehaviorDrivenRecyclerView;
import com.seemingamusing.android.playground.common.MockedDataAdapter;

public class FooterActivity extends AppCompatActivity {

  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.content_view) BehaviorDrivenRecyclerView mContentView;
  @Bind(R.id.footer_bar) View mFooterBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_footer);
    ButterKnife.bind(this);
    setTitle(R.string.sample_footer);
    setSupportActionBar(mToolbar);
    initializeContentView();
  }

  @Override public void setSupportActionBar(Toolbar toolbar) {
    mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        finish();
      }
    });
    super.setSupportActionBar(toolbar);
  }

  private void initializeContentView() {
    mContentView.setBehavior(new FooterBarBehavior());
    mContentView.setLayoutManager(new LinearLayoutManager(this));
    mContentView.setAdapter(new MockedDataAdapter(this));
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_toggleable_element, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_show) {
      toggleFooter(item);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void toggleFooter(MenuItem item) {
    boolean isShowing = mFooterBar.getVisibility() == View.VISIBLE;
    if (isShowing) {
      hideFooter();
      item.setTitle(R.string.action_show);
    } else {
      mFooterBar.setVisibility(View.VISIBLE);
      item.setTitle(R.string.action_hide);
    }
  }

  @OnClick(R.id.footer_bar) public void hideFooter() {
    mFooterBar.setVisibility(View.GONE);
  }
}