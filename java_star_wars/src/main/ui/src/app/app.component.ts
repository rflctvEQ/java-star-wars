import { Component, OnInit, OnDestroy } from '@angular/core';
import { AppService } from './services/app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit, OnDestroy {
  constructor(private appService: AppService) {}

  title = 'Java Star Wars';

  // TODO: create interface here instead of using any[]
  jedi: any[] = [];

  destroy$: Subject<boolean> = new Subject<boolean>();

  ngOnInit() {
    this.getJedi();
  }

  getJedi() {
    this.appService
      .getJedi()
      .pipe(takeUntil(this.destroy$))
      .subscribe((jediRetrieved: any) => {
        this.jedi = jediRetrieved;
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }
}
